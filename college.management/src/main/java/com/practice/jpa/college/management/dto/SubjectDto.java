package com.practice.jpa.college.management.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class SubjectDto {
    String title;
    ProfessorDto professor ;
}
