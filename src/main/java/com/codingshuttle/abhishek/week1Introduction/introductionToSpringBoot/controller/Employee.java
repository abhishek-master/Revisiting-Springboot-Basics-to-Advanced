package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.controller;

import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.DTO.EmployeeDTO;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employee")
public class Employee {

    EmployeeService employeeService ;

    public Employee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    List<EmployeeDTO> getAllEmployees (){
        return employeeService.getAllEmployees();
    }


    @PutMapping("/{employeeId}")
    EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeData, @PathVariable Integer employeeId) {
       return  employeeService.updateEmployee(employeeData, employeeId);
    }

    /*
    * Briefly check what is Java Reflection here :
    * https://www.oracle.com/technical-resources/articles/java/javareflection.html
    * */
    @PatchMapping("/{employeeId}")
    EmployeeDTO patchEmployee(@RequestBody Map<String, Object> updates, @PathVariable Integer employeeId) {
        return employeeService.updatePartialEmployeeById(updates, employeeId);
    }

    @DeleteMapping("/{employeeId}")
    boolean deleteEmployee(@PathVariable Integer employeeId) {
        return employeeService.deleteEmployee(employeeId);
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
