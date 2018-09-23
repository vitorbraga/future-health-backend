package com.futurehealth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO {

    private Long id;

    @NotNull(message = "First name can not be null!")
    private String firstName;

    @NotNull(message = "Last name can not be null!")
    private String lastName;

    @NotNull(message = "Email can not be null!")
    @Email
    private String email;

    private String password;

    private String birthday;

    @NotNull(message = "Registry can not be null!")
    private String registry;

    private String facebookUrl;

    private String twitterUrl;

    private Instant createdAt;

    private Instant updatedAt;

    private List<ExperienceDTO> experiences;

    private List<EducationDTO> educations;
}
