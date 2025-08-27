package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.service;

import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.DTO.EmployeeDTO;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.entity.EmployeeEntity;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
        return toReturnEntity.map(toReturnEntity1 -> modelMapper.map(toReturnEntity1, EmployeeDTO.class)) ;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO toSaveEntity) {
        EmployeeEntity employeeEntity = modelMapper.map(toSaveEntity, EmployeeEntity.class);
        EmployeeEntity savedEmployee = employeeRepo.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);

    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeData, Integer employeeId) {
        boolean isExist = employeeRepo.existsById(employeeId);
        if (isExist) {
            EmployeeEntity employeeEntity = modelMapper.map(employeeData, EmployeeEntity.class);
            employeeEntity.setId(employeeId);
            employeeRepo.save(employeeEntity);
            return modelMapper.map(employeeEntity, EmployeeDTO.class);
        }
        return null;
    }

    public Boolean deleteEmployee(Integer employeeId) {
        boolean exists = employeeRepo.existsById(employeeId);
        if(exists){
        employeeRepo.deleteById(employeeId);
        }
        return exists ;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        return employeeEntities.stream()
                                    .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)).toList();
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Integer employeeId) {
        boolean isExists =  employeeRepo.existsById(employeeId);
        if(!isExists){return null ;}
        EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepo.save(employeeEntity),  EmployeeDTO.class);
    }
}

