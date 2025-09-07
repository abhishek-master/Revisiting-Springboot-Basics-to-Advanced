package com.revisit.mapping.transaction.orphanremoval.jpa.repository;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface DoctorRepository extends JpaRepository<Doctor, BigInteger> {
}
