package com.futurehealth.repository;

import com.futurehealth.domain.Experience;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExperienceRepository extends CrudRepository<Experience, Long> {

    List<Experience> findAllByDoctorId(Long doctorId);
}
