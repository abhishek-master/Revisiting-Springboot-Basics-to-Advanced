package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.controller;

import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.DTO.EmployeeDTO;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.entity.EmployeeEntity;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.repository.EmployeeRepo;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/employee")
public class Employee {

    //EmployeeRepo  employeeRepo;
    EmployeeService employeeService ;

    public Employee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    Optional<EmployeeDTO> getEmployee (@PathVariable  Integer employeeId) {
       return employeeService.getById(employeeId);

    }

    @GetMapping("/")
    String getIdViaParams(@RequestParam(required = true) Integer employeeId, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return "This is response : " + employeeId + " and " + firstName + " and " + lastName ;
    }
//    @GetMapping("/getEmployeesById/{age}")
//    Optional<EmployeeEntity> getAllEmployees(@PathVariable  Integer age) {
//        return employeeRepo.findById(age);
//    }
//    @PostMapping("/")
//    EmployeeEntity saveEmployee(@RequestBody EmployeeEntity employeeDTO) {
//        return employeeRepo.save(employeeDTO);
//    }
}
