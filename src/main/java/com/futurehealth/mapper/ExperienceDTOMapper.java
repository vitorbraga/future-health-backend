package com.futurehealth.mapper;

import com.futurehealth.domain.Doctor;
import com.futurehealth.domain.Experience;
import com.futurehealth.dto.DoctorDTO;
import com.futurehealth.dto.ExperienceDTO;

import java.util.Optional;

public class ExperienceDTOMapper {

    public static ExperienceDTO makeExperienceDTO(Experience experience) {
        return Optional.ofNullable(experience)
                .map(e -> ExperienceDTO.builder()
                        .id(e.getId())
                        .title(e.getTitle())
                        .description(e.getDescription())
                        .location(e.getLocation())
                        .startDate(e.getStartDate())
                        .endDate(e.getEndDate())
                        .build()
                ).orElse(null);
    }

    public static Experience makeExperienceFromDTO(ExperienceDTO experienceDTO) {
        return Optional.ofNullable(experienceDTO)
                .map(dto -> {
                    Experience experience = new Experience();
                    experience.setId(dto.getId());
                    experience.setTitle(dto.getTitle());
                    experience.setDescription(dto.getDescription());
                    experience.setLocation(dto.getLocation());
                    experience.setStartDate(dto.getStartDate());
                    experience.setEndDate(dto.getEndDate());

                    return experience;
                }).orElse(null);
    }
}
