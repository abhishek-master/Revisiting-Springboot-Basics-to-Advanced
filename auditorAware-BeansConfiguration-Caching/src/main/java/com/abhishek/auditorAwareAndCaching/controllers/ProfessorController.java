package com.abhishek.auditorAwareAndCaching.controllers;

import com.abhishek.auditorAwareAndCaching.dto.GetAllProfessorsResponseDto;
import com.abhishek.auditorAwareAndCaching.dto.ProfessorDto;
import com.abhishek.auditorAwareAndCaching.dto.ProfessorResponseDto;
import com.abhishek.auditorAwareAndCaching.services.ProfessorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

    private final ProfessorService professorService ;
    private final ModelMapper modelMapper;

    ProfessorController(ProfessorService professorService, ModelMapper modelMapper) {
        this.professorService = professorService ;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<GetAllProfessorsResponseDto>> getAllProfessor () {
        List<GetAllProfessorsResponseDto> res = professorService.getAllProfessors() ;
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable Integer id){
        return new ResponseEntity<>(professorService.getProfessorById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public  ResponseEntity<ProfessorDto> createProfessor (@RequestBody ProfessorDto data) {
        return new ResponseEntity<>(professorService.createProfessor(data), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> updateProfessor (@PathVariable Integer id, @RequestBody Map<String, List<Integer>> data) {
        return new ResponseEntity<>(professorService.updateProfessor(id, data), HttpStatus.OK);
    }

}
