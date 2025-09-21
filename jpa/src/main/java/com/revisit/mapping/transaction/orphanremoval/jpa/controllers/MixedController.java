package com.revisit.mapping.transaction.orphanremoval.jpa.controllers;

import com.revisit.mapping.transaction.orphanremoval.jpa.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jpa")
public class MixedController {

    DoctorService doctorService ;

    MixedController (DoctorService doctorService) {
        this.doctorService = doctorService ;
    }

    @GetMapping(path = "getAllDocs")
    public ResponseEntity<List<Map<String, String>>> getAllDoctors () {

         return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK) ;
    }
}
