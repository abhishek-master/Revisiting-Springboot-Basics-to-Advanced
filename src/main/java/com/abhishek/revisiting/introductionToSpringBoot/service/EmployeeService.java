package com.abhishek.revisiting.introductionToSpringBoot.service;

import com.abhishek.revisiting.introductionToSpringBoot.DTO.EmployeeDTO;
import com.abhishek.revisiting.introductionToSpringBoot.entity.EmployeeEntity;
import com.abhishek.revisiting.introductionToSpringBoot.exceptions.ResourceNotFound;
import com.abhishek.revisiting.introductionToSpringBoot.repository.EmployeeRepo;
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
        return toReturnEntity.map(toReturnEntity1 -> modelMapper.map(toReturnEntity1, EmployeeDTO.class)) ;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO toSaveEntity) {
        EmployeeEntity employeeEntity = modelMapper.map(toSaveEntity, EmployeeEntity.class);
        EmployeeEntity savedEmployee = employeeRepo.save(employeeEntity);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);

    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeData, Integer employeeId) {
        if (employeeExists(employeeId)) {
            EmployeeEntity employeeEntity = modelMapper.map(employeeData, EmployeeEntity.class);
            employeeEntity.setId(employeeId);
            employeeRepo.save(employeeEntity);
            return modelMapper.map(employeeEntity, EmployeeDTO.class);
        }
        return null;
    }

    public Boolean deleteEmployee(Integer employeeId) {
        if(employeeExists(employeeId)){
        employeeRepo.deleteById(employeeId);
        return true ;
        }
        return false;

    }

    public boolean employeeExists (int id){
        if(!employeeRepo.existsById(id)){
            throw  new ResourceNotFound("No Employee found for id: " + id) ;
        }
        return true ;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        return employeeEntities.stream()
                                    .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class)).toList();
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Integer employeeId) {
        if(employeeExists(employeeId)) {
            EmployeeEntity employeeEntity = employeeRepo.findById(employeeId).get();
            updates.forEach((field, value) -> {
                Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            });
            return modelMapper.map(employeeRepo.save(employeeEntity),  EmployeeDTO.class);
        }
        return null ;
    }
}

