package com.revisit.mapping.transaction.orphanremoval.jpa.repository;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;

public interface InsuranceRepository extends JpaRepository<Insurance, BigInteger> {
}
