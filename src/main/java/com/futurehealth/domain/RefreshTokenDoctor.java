package com.futurehealth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_token_doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDoctor {

    @Id
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    private Instant expirationDateTime;

    public RefreshTokenDoctor(String token) {
        this.token = token;
    }

}
