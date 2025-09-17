package com.practice.jpa.college.management.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionRecordDto {
    Integer fees ;
    StudentDto student;
}
