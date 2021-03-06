package com.futurehealth.controller;

import com.futurehealth.dto.DoctorDTO;
import com.futurehealth.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{doctorId}")
    public DoctorDTO getById(@Valid @PathVariable Long doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping
    public DoctorDTO createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        return doctorService.createDoctor(doctorDTO);
    }

    @PutMapping("/{doctorId}")
    public DoctorDTO update(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.updateDoctor(doctorDTO);
    }

    @DeleteMapping("/{doctorId}")
    public void deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
    }

}
