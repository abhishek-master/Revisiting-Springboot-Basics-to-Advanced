package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.repository;

import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {


}
