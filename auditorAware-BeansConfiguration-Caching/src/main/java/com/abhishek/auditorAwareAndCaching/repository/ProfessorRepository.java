package com.abhishek.auditorAwareAndCaching.repository;

import com.abhishek.auditorAwareAndCaching.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
