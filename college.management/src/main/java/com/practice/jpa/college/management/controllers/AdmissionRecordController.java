package com.practice.jpa.college.management.controllers;

import com.practice.jpa.college.management.dto.AdmissionRecordDto;
import com.practice.jpa.college.management.dto.AdmissionRecordRequestDto;
import com.practice.jpa.college.management.entities.AdmissionRecord;
import com.practice.jpa.college.management.services.AdmissionRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admissionRecord")
public class AdmissionRecordController {

    AdmissionRecordService admissionRecordService ;
    AdmissionRecordController (AdmissionRecordService admissionRecordService ) {
        this.admissionRecordService = admissionRecordService ;
    }
    @PostMapping("/")
    public ResponseEntity<AdmissionRecordDto> createAdmissionRecord (@RequestBody AdmissionRecordRequestDto data) {
        return new ResponseEntity<>(admissionRecordService.createNewAdmissionRecord(data), HttpStatus.CREATED);
    }
}
