package com.abhishek.revisiting.introductionToSpringBoot.repository;

import com.abhishek.revisiting.introductionToSpringBoot.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {
}
