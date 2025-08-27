package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.controller;

import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.DTO.EmployeeDTO;
import com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.service.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/employee")
public class Employee {

    private final ModelMapper modelMapper;
    EmployeeService employeeService ;

    public Employee(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    ResponseEntity<List<EmployeeDTO>> getAllEmployees (){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }


    @PutMapping("/{employeeId}")
    ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeData, @PathVariable Integer employeeId) {
       EmployeeDTO employeeDTO = employeeService.updateEmployee(employeeData, employeeId);
       if(employeeDTO!=null){
           return ResponseEntity.ok(employeeDTO);
       }
       return ResponseEntity.notFound().build();
    }

    /*
    * Briefly check what is Java Reflection here :
    * https://www.oracle.com/technical-resources/articles/java/javareflection.html
    * */
    @PatchMapping("/{employeeId}")
    ResponseEntity<EmployeeDTO> patchEmployee(@RequestBody Map<String, Object> updates, @PathVariable Integer employeeId) {
        EmployeeDTO employeeDTO =  employeeService.updatePartialEmployeeById(updates, employeeId);
        if(employeeDTO!=null){
            return ResponseEntity.ok(employeeDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{employeeId}")
    ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer employeeId) {
        return employeeService.deleteEmployee(employeeId) ? ResponseEntity.noContent().build(): ResponseEntity.notFound().build();
    }

    @GetMapping("/{employeeId}")
    ResponseEntity<EmployeeDTO> getEmployee (@PathVariable  Integer employeeId) {
        System.out.println("getEmployee "+employeeId);
       Optional<EmployeeDTO> employeeDTO =  employeeService.getById(employeeId);
       return employeeDTO
               .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO employeeData) {
        EmployeeDTO employeeDTO = employeeService.saveEmployee(employeeData);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }
}
