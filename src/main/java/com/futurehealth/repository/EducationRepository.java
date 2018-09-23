package com.futurehealth.repository;

import com.futurehealth.domain.Education;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EducationRepository extends CrudRepository<Education, Long> {

    List<Education> findAllByDoctorId(Long doctorId);
}
