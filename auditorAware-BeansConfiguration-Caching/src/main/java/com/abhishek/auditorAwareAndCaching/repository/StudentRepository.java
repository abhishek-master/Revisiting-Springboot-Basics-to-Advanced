package com.abhishek.auditorAwareAndCaching.repository;

import com.abhishek.auditorAwareAndCaching.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
