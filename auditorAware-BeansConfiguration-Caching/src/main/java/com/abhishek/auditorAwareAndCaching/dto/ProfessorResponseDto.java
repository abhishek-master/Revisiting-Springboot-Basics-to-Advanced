package com.abhishek.auditorAwareAndCaching.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class ProfessorResponseDto {
    String title ;
    List<SubjectTitle> subjects;
    List<StudentName> students;

}
