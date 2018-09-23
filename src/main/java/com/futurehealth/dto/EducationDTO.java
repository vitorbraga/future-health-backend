package com.futurehealth.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EducationDTO {

    private Long id;

    @NotNull(message = "School can not be null!")
    private String school;

    @NotNull(message = "Title can not be null!")
    private String title;

    @NotNull(message = "Description can not be null!")
    private String description;

    @NotNull(message = "Start date can not be null!")
    private String startDate;

    @NotNull(message = "End date can not be null!")
    private String endDate;

}
