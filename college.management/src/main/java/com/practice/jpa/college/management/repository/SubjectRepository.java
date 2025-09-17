package com.practice.jpa.college.management.repository;

import com.practice.jpa.college.management.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
