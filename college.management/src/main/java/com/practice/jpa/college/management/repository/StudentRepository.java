package com.practice.jpa.college.management.repository;

import com.practice.jpa.college.management.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
