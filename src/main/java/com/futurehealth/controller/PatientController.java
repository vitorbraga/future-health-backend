package com.futurehealth.controller;

import com.futurehealth.dto.PatientDTO;
import com.futurehealth.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}")
    public PatientDTO getById(@PathVariable Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @PostMapping
    public PatientDTO createPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.createPatient(patientDTO);
    }

    @PutMapping("/{patientId}")
    public PatientDTO updatePatient(@RequestBody PatientDTO patientDTO) {
        return patientService.updatePatient(patientDTO);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
    }
}
