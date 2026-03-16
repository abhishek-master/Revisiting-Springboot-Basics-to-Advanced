package com.abhishek.auditorAwareAndCaching.repository;

import com.abhishek.auditorAwareAndCaching.entities.AdmissionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecord, Integer> {
}
