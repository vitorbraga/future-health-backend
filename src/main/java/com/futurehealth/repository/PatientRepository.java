package com.futurehealth.repository;

import com.futurehealth.domain.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
