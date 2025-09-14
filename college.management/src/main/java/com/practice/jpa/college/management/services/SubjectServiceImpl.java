package com.practice.jpa.college.management.services;

import com.practice.jpa.college.management.dto.StudentDto;
import com.practice.jpa.college.management.dto.SubjectDto;
import com.practice.jpa.college.management.dto.UpdateSubjectRequestDto;
import com.practice.jpa.college.management.entities.Professor;
import com.practice.jpa.college.management.entities.Student;
import com.practice.jpa.college.management.entities.Subject;
import com.practice.jpa.college.management.repository.ProfessorRepository;
import com.practice.jpa.college.management.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final ModelMapper modelMapper;
    private final SubjectRepository subjectRepository ;
    private final ProfessorRepository professorRepository;

    public SubjectServiceImpl(ModelMapper modelMapper, SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository ;
        this.professorRepository = professorRepository;
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectData) {
        return modelMapper.map(subjectRepository.save(modelMapper.map(subjectData, Subject.class)), SubjectDto.class);
    }

    @Override
    public SubjectDto updateSubject(Integer id, UpdateSubjectRequestDto body) {
        Subject subjectToBeUpdated = subjectRepository.findById(id).orElseThrow();
        Professor professor = professorRepository.findById(body.getProfessorId()).orElseThrow();
        subjectToBeUpdated.setProfessor(professor);
        subjectRepository.save(subjectToBeUpdated);
        System.out.println( " Professors Subject  " + professor.getSubjects());
        return modelMapper.map(subjectToBeUpdated, SubjectDto.class);
    }
}
