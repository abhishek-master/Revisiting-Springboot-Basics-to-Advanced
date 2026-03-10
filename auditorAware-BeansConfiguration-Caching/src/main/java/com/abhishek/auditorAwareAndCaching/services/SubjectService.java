package com.abhishek.auditorAwareAndCaching.services;

import com.abhishek.auditorAwareAndCaching.dto.SubjectDto;
import com.abhishek.auditorAwareAndCaching.dto.UpdateSubjectRequestDto;


public interface SubjectService {
    SubjectDto createSubject (SubjectDto subjectData);

    SubjectDto updateSubject(Integer id, UpdateSubjectRequestDto body);

    SubjectDto getSubject(Integer subjectId);
}
