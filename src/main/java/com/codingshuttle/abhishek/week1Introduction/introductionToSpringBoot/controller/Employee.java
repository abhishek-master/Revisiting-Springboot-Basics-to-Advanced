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

    EmployeeService employeeService ;

    public Employee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/{employeeId}")
    EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeData, @PathVariable Integer employeeId) {
       return  employeeService.updateEmployee(employeeData, employeeId);
    }

//    @PatchMapping("/{employeeId}")
//    EmployeeDTO patchEmployee(@RequestBody Object data, @PathVariable Integer employeeId) {
//
//    }

    @DeleteMapping("/{employeeId}")
    void deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/{employeeId}")
    Optional<EmployeeDTO> getEmployee (@PathVariable  Integer employeeId) {
        System.out.println("getEmployee "+employeeId);
       return employeeService.getById(employeeId);
    }

    @PostMapping("/")
    EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeData) {
        return employeeService.saveEmployee(employeeData);
    }
}
