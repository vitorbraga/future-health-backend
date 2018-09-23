package com.futurehealth.service;

import com.futurehealth.domain.Doctor;
import com.futurehealth.domain.Experience;
import com.futurehealth.dto.ExperienceDTO;
import com.futurehealth.mapper.ExperienceDTOMapper;
import com.futurehealth.repository.DoctorRepository;
import com.futurehealth.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Service
public class ExperienceService {

    private ExperienceRepository experienceRepository;

    private DoctorRepository doctorRepository;

    @Autowired
    public ExperienceService(ExperienceRepository experienceRepository, DoctorRepository doctorRepository) {
        this.experienceRepository = experienceRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public ExperienceDTO createExperience(Long doctorId, ExperienceDTO experienceDTO) {

        Experience newExperience = ExperienceDTOMapper.makeExperienceFromDTO(experienceDTO);

        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);

        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();

            newExperience.setDoctor(doctor);

            emptyIfNull(doctor.getExperiences()).add(newExperience);

            doctorRepository.save(doctor);
        }

        return null;
    }

    public List<ExperienceDTO> getDoctorExperiences(Long doctorId) {
        return experienceRepository.findAllByDoctorId(doctorId)
                .stream()
                .map(ExperienceDTOMapper::makeExperienceDTO)
                .collect(Collectors.toList());
    }

    public ExperienceDTO getExperienceById(Long experienceId) {
        return ExperienceDTOMapper.makeExperienceDTO(experienceRepository.findById(experienceId).orElse(null));
    }

    @Transactional
    public ExperienceDTO updateExperience(ExperienceDTO experienceDTO) {
        Experience experience = experienceRepository.save(ExperienceDTOMapper.makeExperienceFromDTO(experienceDTO));

        return ExperienceDTOMapper.makeExperienceDTO(experience);
    }

    @Transactional
    public void deleteExperience(Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }

}
