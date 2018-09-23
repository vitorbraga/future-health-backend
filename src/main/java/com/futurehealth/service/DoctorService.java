package com.futurehealth.service;

import com.futurehealth.domain.Doctor;
import com.futurehealth.dto.DoctorDTO;
import com.futurehealth.mapper.DoctorDTOMapper;
import com.futurehealth.repository.DoctorRepository;
import com.futurehealth.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
        newDoctor = UserUtils.setPasswordEncoded(newDoctor, doctorDTO.getPassword());

        return DoctorDTOMapper.makeDoctorDTO(doctorRepository.save(newDoctor));
    }

    @Transactional
    public DoctorDTO updateDoctor(DoctorDTO doctorDTO) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorDTO.getId());

        if (!doctorOptional.isPresent()) {
            return null;
        }

        Doctor updatedDoctor = DoctorDTOMapper.makeDoctorFromDTO(doctorDTO);
        updatedDoctor.setPassword(doctorOptional.get().getPassword());

        return DoctorDTOMapper.makeDoctorDTO(doctorRepository.save(updatedDoctor));
    }

    @Transactional
    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

}
