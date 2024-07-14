package com.rungroup.api.repository;

import com.rungroup.api.entity.Patient;
import com.rungroup.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String>, JpaSpecificationExecutor<Patient> {
    Optional<Patient> findFirstByUserAndId(User user, String id);
}
