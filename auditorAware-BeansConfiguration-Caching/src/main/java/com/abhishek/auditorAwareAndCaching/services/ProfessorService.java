package com.abhishek.auditorAwareAndCaching.services;

import com.abhishek.auditorAwareAndCaching.dto.GetAllProfessorsResponseDto;
import com.abhishek.auditorAwareAndCaching.dto.ProfessorDto;
import com.abhishek.auditorAwareAndCaching.dto.ProfessorResponseDto;
import com.abhishek.auditorAwareAndCaching.entities.Professor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface ProfessorService {

    List<GetAllProfessorsResponseDto> getAllProfessors ();

    ProfessorDto getProfessorById (Integer profesor_id);

    ProfessorDto createProfessor (ProfessorDto data) ;

    ProfessorResponseDto updateProfessor (Integer id, Map<String, List<Integer>> data );

}
