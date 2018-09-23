package com.futurehealth.repository;

import com.futurehealth.domain.RefreshTokenDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenDoctorRepository extends JpaRepository<RefreshTokenDoctor, String> {

}
