package com.futurehealth.service;

import com.futurehealth.domain.Doctor;
import com.futurehealth.dto.DoctorDTO;
import com.futurehealth.mapper.DoctorDTOMapper;
import com.futurehealth.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorDTO getDoctorById(Long doctorId) {
        return DoctorDTOMapper.makeDoctorDTO(doctorRepository.findById(doctorId).orElse(null));
    }

    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {

        Doctor newDoctor = DoctorDTOMapper.makeDoctorFromDTO(doctorDTO);
        newDoctor = setTimestampsAndPasswordEncoded(newDoctor);

        return DoctorDTOMapper.makeDoctorDTO(doctorRepository.save(newDoctor));
    }

    private Doctor setTimestampsAndPasswordEncoded(Doctor doctor) {
        doctor.setPassword("senhateste"); // FIXME
        doctor.setCreatedAt(Instant.now());
        doctor.setUpdatedAt(Instant.now());

        return doctor;
    }

    public void updateDoctor(DoctorDTO doctorDTO) {

    }

    @Transactional
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

}
