package com.practice.jpa.college.management.services;

import com.practice.jpa.college.management.dto.AdmissionRecordDto;
import com.practice.jpa.college.management.dto.AdmissionRecordRequestDto;
import com.practice.jpa.college.management.dto.StudentDto;
import com.practice.jpa.college.management.entities.AdmissionRecord;
import com.practice.jpa.college.management.entities.Student;
import com.practice.jpa.college.management.repository.AdmissionRecordRepository;
import com.practice.jpa.college.management.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdmissionRecordServiceImpl implements AdmissionRecordService {
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    AdmissionRecordRepository admissionRecordRepository ;

        AdmissionRecordServiceImpl (AdmissionRecordRepository admissionRecordRepository, ModelMapper modelMapper, StudentRepository studentRepository) {
            this.admissionRecordRepository = admissionRecordRepository ;
            this.modelMapper = modelMapper;
            this.studentRepository = studentRepository;
        }

    @Override
    public AdmissionRecordDto createNewAdmissionRecord(AdmissionRecordRequestDto data) { //I know the naming convention is not correct
            AdmissionRecord admissionRecord = modelMapper.map(AdmissionRecordRequestDto.builder().fees(data.getFees()).build(), AdmissionRecord.class);
            Student student = studentRepository.findById(data.getStudentId()).orElseThrow();
            admissionRecord.setStudent(student);
            student.setAdmissionRecord(admissionRecord);
            admissionRecordRepository.save(admissionRecord);
            return modelMapper.map(admissionRecord, AdmissionRecordDto.class);
    }
}
