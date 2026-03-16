package com.abhishek.auditorAwareAndCaching.repository;

import com.abhishek.auditorAwareAndCaching.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
