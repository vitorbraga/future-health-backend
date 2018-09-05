package com.futurehealth.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DoctorDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String birthday;

    private String registry;

    private String facebookUrl;

    private String twitterUrl;

}
