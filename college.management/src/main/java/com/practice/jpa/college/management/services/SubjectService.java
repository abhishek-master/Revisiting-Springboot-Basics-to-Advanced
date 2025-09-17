package com.practice.jpa.college.management.services;

import com.practice.jpa.college.management.dto.SubjectDto;
import com.practice.jpa.college.management.dto.UpdateSubjectRequestDto;


public interface SubjectService {
    SubjectDto createSubject (SubjectDto subjectData);

    SubjectDto updateSubject(Integer id, UpdateSubjectRequestDto body);
}
