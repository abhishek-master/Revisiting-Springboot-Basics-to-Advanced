package com.abhishek.auditorAwareAndCaching.services;

import com.abhishek.auditorAwareAndCaching.dto.StudentDto;
import com.abhishek.auditorAwareAndCaching.entities.Student;
import com.abhishek.auditorAwareAndCaching.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    private final ModelMapper modelMapper;
    StudentRepository studentRepository ;

    StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository ;
        this.modelMapper = modelMapper;
    }

    @Override
    public StudentDto createStudent(StudentDto studentData) {
        return modelMapper.map(studentRepository.save(modelMapper.map(studentData, Student.class)), StudentDto.class);
    }
}
