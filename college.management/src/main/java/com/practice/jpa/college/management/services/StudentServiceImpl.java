package com.practice.jpa.college.management.services;

import com.practice.jpa.college.management.dto.StudentDto;
import com.practice.jpa.college.management.entities.Student;
import com.practice.jpa.college.management.repository.StudentRepository;
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
