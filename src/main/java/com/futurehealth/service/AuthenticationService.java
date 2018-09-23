package com.futurehealth.service;

import com.futurehealth.domain.Doctor;
import com.futurehealth.domain.RefreshTokenDoctor;
import com.futurehealth.exception.BadRequestException;
import com.futurehealth.payload.JwtAuthenticationResponse;
import com.futurehealth.payload.LoginRequest;
import com.futurehealth.payload.RefreshTokenRequest;
import com.futurehealth.repository.DoctorRepository;
import com.futurehealth.repository.RefreshTokenDoctorRepository;
import com.futurehealth.security.DoctorPrincipal;
import com.futurehealth.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthenticationService {

    private RefreshTokenDoctorRepository refreshTokenDoctorRepository;

    private DoctorRepository doctorRepository;

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider tokenProvider;

    private Long jwtExpirationInMs;

    @Autowired
    public AuthenticationService(RefreshTokenDoctorRepository refreshTokenDoctorRepository,
                                 DoctorRepository doctorRepository,
                                 AuthenticationManager authenticationManager,
                                 JwtTokenProvider tokenProvider,
                                 @Value("${jwt.expiration-in-ms}") Long jwtExpirationInMs) {
        this.refreshTokenDoctorRepository = refreshTokenDoctorRepository;
        this.doctorRepository = doctorRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public ResponseEntity<?> loginDoctor(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        DoctorPrincipal doctorPrincipal = (DoctorPrincipal) authentication.getPrincipal();
        String accessToken = tokenProvider.generateToken(doctorPrincipal);
        String refreshToken = tokenProvider.generateRefreshToken();

        saveRefreshToken(doctorPrincipal, refreshToken);

        return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, refreshToken, jwtExpirationInMs));
    }

    private void saveRefreshToken(DoctorPrincipal doctorPrincipal, String refreshToken) {
        RefreshTokenDoctor refreshTokenDoctor = new RefreshTokenDoctor(refreshToken);
        refreshTokenDoctor.setDoctor(doctorRepository.getOne(doctorPrincipal.getId()));

        Instant expirationDateTime = Instant.now().plus(360, ChronoUnit.DAYS);  // Todo Add this in application.properties
        refreshTokenDoctor.setExpirationDateTime(expirationDateTime);

        refreshTokenDoctorRepository.save(refreshTokenDoctor);
    }

    public ResponseEntity<?> refreshTokenDoctor(RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenDoctorRepository.findById(refreshTokenRequest.getRefreshToken())
                .map(jwtRefreshToken -> {
                    Doctor doctor = jwtRefreshToken.getDoctor();
                    String accessToken = tokenProvider.generateToken(DoctorPrincipal.create(doctor));

                    return ResponseEntity.ok(new JwtAuthenticationResponse(accessToken, jwtRefreshToken.getToken(),
                            jwtExpirationInMs));
                }).orElseThrow(() -> new BadRequestException("Invalid Refresh Token"));
    }
}
