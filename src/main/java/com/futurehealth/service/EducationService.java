package com.futurehealth.service;

import com.futurehealth.domain.Doctor;
import com.futurehealth.domain.Education;
import com.futurehealth.dto.EducationDTO;
import com.futurehealth.mapper.EducationDTOMapper;
import com.futurehealth.repository.DoctorRepository;
import com.futurehealth.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Service
public class EducationService {

    private EducationRepository educationRepository;

    private DoctorRepository doctorRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository, DoctorRepository doctorRepository) {
        this.educationRepository = educationRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public EducationDTO createEducation(Long doctorId, EducationDTO educationDTO) {

        Education newEducation = EducationDTOMapper.makeEducationFromDTO(educationDTO);

        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();

            newEducation.setDoctor(doctor);

            emptyIfNull(doctor.getEducations()).add(newEducation);

            doctorRepository.save(doctor);
        }

        return null;
    }

    public List<EducationDTO> getDoctorEducations(Long doctorId) {
        return educationRepository.findAllByDoctorId(doctorId)
                .stream()
                .map(EducationDTOMapper::makeEducationDTO)
                .collect(Collectors.toList());
    }

    public EducationDTO getEducationById(Long educationId) {
        return EducationDTOMapper.makeEducationDTO(educationRepository.findById(educationId).orElse(null));
    }

    @Transactional
    public EducationDTO updateEducation(EducationDTO educationDTO) {
        Education education = educationRepository.save(EducationDTOMapper.makeEducationFromDTO(educationDTO));

        return EducationDTOMapper.makeEducationDTO(education);
    }

    @Transactional
    public void deleteEducation(Long educationId) {
        educationRepository.deleteById(educationId);
    }

}
