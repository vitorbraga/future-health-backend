package com.futurehealth.controller;

import com.futurehealth.dto.EducationDTO;
import com.futurehealth.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/educations")
public class EducationController {

    private EducationService educationService;

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/{doctorId}")
    public EducationDTO createEducation(@Valid @PathVariable Long doctorId,
                                        @Valid @RequestBody EducationDTO educationDTO) {
        return educationService.createEducation(doctorId, educationDTO);
    }

    @GetMapping("/doctors/{doctorId}")
    public List<EducationDTO> getDoctorEducations(@Valid @PathVariable Long doctorId) {
        return educationService.getDoctorEducations(doctorId);
    }

    @GetMapping("/{educationId}")
    public EducationDTO getEducationById(@Valid @PathVariable Long educationId) {
        return educationService.getEducationById(educationId);
    }

    @PutMapping("/{educationId}")
    public EducationDTO updateEducation(@Valid @PathVariable EducationDTO educationDTO) {
        return educationService.updateEducation(educationDTO);
    }

    @DeleteMapping("/{educationId}")
    public void deleteEducation(@Valid @PathVariable Long educationId) {
        educationService.deleteEducation(educationId);
    }
}
