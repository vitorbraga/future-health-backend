package com.futurehealth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PatientDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private String birthday;

    private Instant createdAt;

    private Instant updatedAt;
}
