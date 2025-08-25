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
    final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepo employeeRepo, ModelMapper modelMapper) {
        this.employeeRepo = employeeRepo;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getById(Integer employeeId) {
        Optional<EmployeeEntity> toReturnEntity = employeeRepo.findById(employeeId);
        System.out.println("employeeEntity : " + toReturnEntity);
        EmployeeDTO employeeDTO = modelMapper.map(toReturnEntity.orElse(null), EmployeeDTO.class);

        /* Importance of .orElse() or .get() is
        to get the Value from optional as
        then only you can map using model mapper. */

        return  Optional.of(employeeDTO) ;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO toSaveEntity) {
        EmployeeEntity employeeEntity = modelMapper.map(toSaveEntity, EmployeeEntity.class);
        EmployeeEntity savedEmployee = employeeRepo.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);

    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeData, Integer employeeId) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeData, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        employeeRepo.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeRepo.deleteById(employeeId);
    }
}
