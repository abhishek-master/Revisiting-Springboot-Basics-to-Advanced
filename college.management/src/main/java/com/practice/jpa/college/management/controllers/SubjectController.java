package com.practice.jpa.college.management.controllers;

import com.practice.jpa.college.management.dto.SubjectDto;
import com.practice.jpa.college.management.dto.UpdateSubjectRequestDto;
import com.practice.jpa.college.management.entities.Subject;
import com.practice.jpa.college.management.repository.SubjectRepository;
import com.practice.jpa.college.management.services.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final ModelMapper modelMapper;
    private final SubjectService subjectService;
    SubjectRepository subjectRepository ;
    SubjectController (SubjectRepository subjectRepository, ModelMapper modelMapper, SubjectService subjectService) {
        this.subjectRepository = subjectRepository ;
        this.modelMapper = modelMapper ;
        this.subjectService = subjectService;
    }

    @PostMapping("/")
    public ResponseEntity<SubjectDto> createSubject (@RequestBody SubjectDto subjectInfo) {
        SubjectDto response = subjectService.createSubject(subjectInfo);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject (@PathVariable Integer id, @RequestBody UpdateSubjectRequestDto body) {
        SubjectDto res = subjectService.updateSubject(id, body);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
