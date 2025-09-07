package com.revisit.mapping.transaction.orphanremoval.jpa.repository;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PatientRepository extends JpaRepository<Patient, BigInteger> {
    boolean removeById(BigInteger id);
}
