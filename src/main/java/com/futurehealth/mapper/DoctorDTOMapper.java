package com.futurehealth.mapper;

import com.futurehealth.domain.Doctor;
import com.futurehealth.dto.DoctorDTO;

import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorDTOMapper {

    public static DoctorDTO makeDoctorDTO(Doctor doctor) {
        return Optional.ofNullable(doctor)
                .map(d -> DoctorDTO.builder()
                        .id(d.getId())
                        .firstName(d.getFirstName())
                        .lastName(d.getLastName())
                        .email(d.getEmail())
                        .birthday(d.getBirthday())
                        .registry(d.getRegistry())
                        .facebookUrl(d.getFacebookUrl())
                        .twitterUrl(d.getTwitterUrl())
                        .createdAt(d.getCreatedAt())
                        .updatedAt(d.getUpdatedAt())
                        .experiences(d.getExperiences()
                                .stream()
                                .map(ExperienceDTOMapper::makeExperienceDTO)
                                .collect(Collectors.toList()))
                        .educations(d.getEducations()
                                .stream()
                                .map(EducationDTOMapper::makeEducationDTO)
                                .collect(Collectors.toList()))
                        .build()
                ).orElse(null);
    }

    public static Doctor makeDoctorFromDTO(DoctorDTO doctorDTO) {
        return Optional.ofNullable(doctorDTO)
                .map(dto -> {
                    Doctor doctor = new Doctor();
                    doctor.setId(dto.getId());
                    doctor.setFirstName(dto.getFirstName());
                    doctor.setLastName(dto.getLastName());
                    doctor.setEmail(dto.getEmail());
                    doctor.setBirthday(dto.getBirthday());
                    doctor.setRegistry(dto.getRegistry());
                    doctor.setFacebookUrl(dto.getFacebookUrl());
                    doctor.setTwitterUrl(dto.getTwitterUrl());
                    return doctor;
                }).orElse(null);
    }
}
