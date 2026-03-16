package com.abhishek.auditorAwareAndCaching.controllers;

import com.abhishek.auditorAwareAndCaching.dto.SubjectDto;
import com.abhishek.auditorAwareAndCaching.dto.UpdateSubjectRequestDto;
import com.abhishek.auditorAwareAndCaching.entities.Subject;
import com.abhishek.auditorAwareAndCaching.repository.SubjectRepository;
import com.abhishek.auditorAwareAndCaching.services.SubjectService;
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

    public ResponseEntity<SubjectDto> getSubject (@RequestParam Integer subjectId){
        SubjectDto response = subjectService.getSubject(subjectId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(200));
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
