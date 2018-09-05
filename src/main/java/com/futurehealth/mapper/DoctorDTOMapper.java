package com.futurehealth.mapper;

import com.futurehealth.domain.Doctor;
import com.futurehealth.dto.DoctorDTO;

import java.util.Optional;

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
                        .build()
                ).orElse(null);
    }

    public static Doctor makeDoctorFromDTO(DoctorDTO doctorDTO) {
        return Optional.ofNullable(doctorDTO)
                .map(dto -> {
                        Doctor doctor = new Doctor();
                        doctor.setEmail(dto.getEmail());
                        doctor.setFirstName(dto.getFirstName());
                        doctor.setLastName(dto.getLastName());
                        doctor.setBirthday(dto.getBirthday());
                        doctor.setRegistry(dto.getRegistry());

                        return doctor;
                    }
                ).orElse(null);
    }
}
