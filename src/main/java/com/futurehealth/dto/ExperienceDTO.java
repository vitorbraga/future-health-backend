package com.futurehealth.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExperienceDTO {

    private Long id;

    @NotNull(message = "Ttile can not be null!")
    private String title;

    @NotNull(message = "Description can not be null!")
    private String description;

    @NotNull(message = "Start date can not be null!")
    private String startDate;

    @NotNull(message = "End date can not be null!")
    private String endDate;

    @NotNull(message = "Location can not be null!")
    private String location;

}
