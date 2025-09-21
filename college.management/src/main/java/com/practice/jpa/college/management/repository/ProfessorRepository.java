package com.practice.jpa.college.management.repository;

import com.practice.jpa.college.management.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
