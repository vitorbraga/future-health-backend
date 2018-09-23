package com.futurehealth.mapper;

import com.futurehealth.domain.Patient;
import com.futurehealth.dto.PatientDTO;

import java.util.Optional;

public class PatientDTOMapper {

    public static PatientDTO makePatientDTO(Patient patient) {
        return Optional.ofNullable(patient)
                .map(d -> PatientDTO.builder()
                        .id(d.getId())
                        .firstName(d.getFirstName())
                        .lastName(d.getLastName())
                        .email(d.getEmail())
                        .birthday(d.getBirthday())
                        .createdAt(d.getCreatedAt())
                        .updatedAt(d.getUpdatedAt())
                        .build()
                ).orElse(null);
    }

    public static Patient makePatientFromDTO(PatientDTO patientDTO) {
        return Optional.ofNullable(patientDTO)
                .map(dto -> {
                    Patient patient = new Patient();
                    patient.setId(dto.getId());
                    patient.setFirstName(dto.getFirstName());
                    patient.setLastName(dto.getLastName());
                    patient.setEmail(dto.getEmail());
                    patient.setBirthday(dto.getBirthday());

                    return patient;
                }).orElse(null);
    }
}
