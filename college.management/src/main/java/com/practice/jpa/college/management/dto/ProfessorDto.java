package com.practice.jpa.college.management.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ProfessorDto {
    String title ;
    List<SubjectDto> subjects ;
}
