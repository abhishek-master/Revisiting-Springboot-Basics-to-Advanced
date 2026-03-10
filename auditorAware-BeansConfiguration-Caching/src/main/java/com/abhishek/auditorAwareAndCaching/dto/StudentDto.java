package com.abhishek.auditorAwareAndCaching.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    String name ;
    List<ProfessorDto> professors ;
    List<SubjectDto> subjects ;
}
