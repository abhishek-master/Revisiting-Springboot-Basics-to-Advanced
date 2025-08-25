package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.service;

import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.DTO.EmployeeDTO;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.entity.EmployeeEntity;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    EmployeeRepo employeeRepo;
    ModelMapper modelMapper;
    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getById(Integer employeeId) {
        Optional<EmployeeEntity> toReturnEntity = employeeRepo.findById(employeeId);
        EmployeeDTO employeeDTO = modelMapper.map(toReturnEntity, EmployeeDTO.class);
        return  Optional.of(employeeDTO) ;
    }
}
