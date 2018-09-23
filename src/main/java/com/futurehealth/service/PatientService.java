package com.futurehealth.service;

import com.futurehealth.domain.Patient;
import com.futurehealth.dto.PatientDTO;
import com.futurehealth.mapper.PatientDTOMapper;
import com.futurehealth.repository.PatientRepository;
import com.futurehealth.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDTO getPatientById(Long patientId) {
        return PatientDTOMapper.makePatientDTO(patientRepository.findById(patientId).orElse(null));
    }

    @Transactional
    public PatientDTO createPatient(PatientDTO patientDTO) {

        Patient newPatient = PatientDTOMapper.makePatientFromDTO(patientDTO);
        newPatient = UserUtils.setPasswordEncoded(newPatient, patientDTO.getPassword());

        return PatientDTOMapper.makePatientDTO(patientRepository.save(newPatient));
    }

    @Transactional
    public PatientDTO updatePatient(PatientDTO patientDTO) {
        Optional<Patient> patientOptional = patientRepository.findById(patientDTO.getId());

        if (!patientOptional.isPresent()) {
            return null;
        }

        Patient updatedPatient = PatientDTOMapper.makePatientFromDTO(patientDTO);
        updatedPatient.setPassword(patientOptional.get().getPassword());

        return PatientDTOMapper.makePatientDTO(patientRepository.save(updatedPatient));
    }

    @Transactional
    public void deletePatient(Long patientId) {
        patientRepository.deleteById(patientId);
    }

}
