package com.practice.jpa.college.management.services;

import com.practice.jpa.college.management.dto.GetAllProfessorsResponseDto;
import com.practice.jpa.college.management.dto.ProfessorDto;
import com.practice.jpa.college.management.dto.ProfessorResponseDto;
import com.practice.jpa.college.management.entities.Professor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ProfessorService {

    List<GetAllProfessorsResponseDto> getAllProfessors ();

    ProfessorDto getProfessorById (Integer profesor_id);

    ProfessorDto createProfessor (ProfessorDto data) ;

    ProfessorResponseDto updateProfessor (Integer id, Map<String, List<Integer>> data );

}
