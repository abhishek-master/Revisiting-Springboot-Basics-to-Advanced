package com.revisit.mapping.transaction.orphanremoval.jpa.repository;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigInteger;

public interface AppointmentRepository extends JpaRepository<Appointment, BigInteger> {
}
