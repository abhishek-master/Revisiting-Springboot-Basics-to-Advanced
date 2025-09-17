package com.practice.jpa.college.management.controllers;

import com.practice.jpa.college.management.dto.StudentDto;
import com.practice.jpa.college.management.services.StudentService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    StudentService studentService ;

    StudentController (StudentService studentService){
        this.studentService = studentService ;
    }

    @PostMapping("/")
    public ResponseEntity<StudentDto> createStudent (@RequestBody StudentDto student_info) {
        StudentDto createdStudent = studentService.createStudent(student_info) ;
        return new ResponseEntity<>(createdStudent, HttpStatusCode.valueOf(201));
    }
}