package com.revisit.mapping.transaction.orphanremoval.jpa.repository;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface DepartmentRepository extends JpaRepository<Department, BigInteger> {
}
