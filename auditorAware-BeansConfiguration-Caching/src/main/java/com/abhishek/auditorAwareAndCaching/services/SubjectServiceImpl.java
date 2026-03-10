package com.abhishek.auditorAwareAndCaching.services;

import com.abhishek.auditorAwareAndCaching.dto.SubjectDto;
import com.abhishek.auditorAwareAndCaching.dto.UpdateSubjectRequestDto;
import com.abhishek.auditorAwareAndCaching.entities.Professor;
import com.abhishek.auditorAwareAndCaching.entities.Subject;
import com.abhishek.auditorAwareAndCaching.repository.ProfessorRepository;
import com.abhishek.auditorAwareAndCaching.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


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

    @Override
    public SubjectDto getSubject(Integer id) {
        Subject sub = subjectRepository.findById(id).orElseThrow();
        return modelMapper.map(sub, SubjectDto.class);
    }
}
