package com.revisiting.jpa.hibernate.spring_data_jpa.controllers;

import com.revisiting.jpa.hibernate.spring_data_jpa.dto.CDepartmentDTO;
import com.revisiting.jpa.hibernate.spring_data_jpa.dto.DepartmentDto;
import com.revisiting.jpa.hibernate.spring_data_jpa.dto.DepartmentResponseDto;
import com.revisiting.jpa.hibernate.spring_data_jpa.dto.IDepartmentDto;
import com.revisiting.jpa.hibernate.spring_data_jpa.service.DepartmentService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/department")
public class DepartmentController {


    private final DepartmentService departmentService ;

    DepartmentController(DepartmentService departmentService){
     this.departmentService = departmentService ;
    }

    @PostMapping("/")
    public ResponseEntity<DepartmentResponseDto> createDepartment (@RequestBody DepartmentDto deptData){
        DepartmentResponseDto result = departmentService.saveDepartment(deptData);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(201)) ;
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<DepartmentResponseDto>> createDepartment (@RequestBody List<DepartmentDto> deptDataList){
        List<DepartmentResponseDto> result = departmentService.saveAllDepartments(deptDataList);
        return new ResponseEntity<>(result, HttpStatusCode.valueOf(201)) ;
    }

    //Controller to see how Interface-Projection works
    @GetMapping("proxy/{id}")
    public ResponseEntity<IDepartmentDto> getDepartmentInfoById (@PathVariable Integer id) {
        IDepartmentDto res = departmentService.getById(id);
        return new ResponseEntity<>(res  , HttpStatusCode.valueOf(200));
    }

    //Controller to see how Class-projection works
    @GetMapping("concrete/{id}")
    public ResponseEntity<CDepartmentDTO> getDepartmentInfoByIdConcrete (@PathVariable Integer id) {
        CDepartmentDTO res = departmentService.getByIdConcrete(id);
        return new ResponseEntity<>(res  , HttpStatusCode.valueOf(200));
    }
}

