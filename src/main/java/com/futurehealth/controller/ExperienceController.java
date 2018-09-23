package com.futurehealth.controller;

import com.futurehealth.dto.ExperienceDTO;
import com.futurehealth.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    private ExperienceService experienceService;

    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping("/{doctorId}")
    public ExperienceDTO createExperience(@Valid @PathVariable Long doctorId,
                                          @Valid @RequestBody ExperienceDTO experienceDTO) {
        return experienceService.createExperience(doctorId, experienceDTO);
    }

    @GetMapping("/doctors/{doctorId}")
    public List<ExperienceDTO> getDoctorExperiences(@Valid @PathVariable Long doctorId) {
        return experienceService.getDoctorExperiences(doctorId);
    }

    @GetMapping("/{experienceId}")
    public ExperienceDTO getExperienceById(@Valid @PathVariable Long experienceId) {
        return experienceService.getExperienceById(experienceId);
    }

    @PutMapping("/{experienceId}")
    public ExperienceDTO updateExperience(@Valid @PathVariable ExperienceDTO experienceDTO) {
        return experienceService.updateExperience(experienceDTO);
    }

    @DeleteMapping("/{experienceId}")
    public void deleteExperience(@Valid @PathVariable Long experienceId) {
        experienceService.deleteExperience(experienceId);
    }
}
