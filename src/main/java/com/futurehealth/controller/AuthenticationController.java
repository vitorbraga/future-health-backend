package com.futurehealth.controller;

import com.futurehealth.payload.LoginRequest;
import com.futurehealth.payload.RefreshTokenRequest;
import com.futurehealth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${jwt.expiration-in-ms}")
    private long jwtExpirationInMs;

    @PostMapping("/login/doctor")
    public ResponseEntity<?> loginDoctor(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.loginDoctor(loginRequest);
    }

    @PostMapping("/logout/doctor")
    public ResponseEntity<?> logoutDoctor(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.loginDoctor(loginRequest);
    }

    @PostMapping("/refresh-token/doctor")
    public ResponseEntity<?> refreshTokenDoctor(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {

        return authenticationService.refreshTokenDoctor(refreshTokenRequest);
    }

}
