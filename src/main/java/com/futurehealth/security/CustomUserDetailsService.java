package com.futurehealth.security;

import com.futurehealth.domain.Doctor;
import com.futurehealth.exception.ResourceNotFoundException;
import com.futurehealth.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Doctor doctor = doctorRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email)
        );

        return DoctorPrincipal.create(doctor);
    }

    @Transactional
    public UserDetails loadUserById(Long id) throws ResourceNotFoundException {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Resource not found")
        );

        return DoctorPrincipal.create(doctor);
    }
}