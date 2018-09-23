package com.futurehealth.mapper;

import com.futurehealth.domain.Education;
import com.futurehealth.dto.EducationDTO;

import java.util.Optional;

public class EducationDTOMapper {

    public static EducationDTO makeEducationDTO(Education education) {
        return Optional.ofNullable(education)
                .map(e -> EducationDTO.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .description(e.getDescription())
                        .school(e.getSchool())
                        .startDate(e.getStartDate())
                        .endDate(e.getEndDate())
                        .build()
                ).orElse(null);
    }

    public static Education makeEducationFromDTO(EducationDTO educationDTO) {
        return Optional.ofNullable(educationDTO)
                .map(dto -> {
                    Education education = new Education();
                    education.setId(dto.getId());
                    education.setTitle(dto.getTitle());
                    education.setDescription(dto.getDescription());
                    education.setSchool(dto.getSchool());
                    education.setStartDate(dto.getStartDate());
                    education.setEndDate(dto.getEndDate());

                    return education;
                }).orElse(null);
    }
}
