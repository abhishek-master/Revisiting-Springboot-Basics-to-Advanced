package com.abhishek.auditorAwareAndCaching.services;

import com.abhishek.auditorAwareAndCaching.dto.AdmissionRecordDto;
import com.abhishek.auditorAwareAndCaching.dto.AdmissionRecordRequestDto;
import com.abhishek.auditorAwareAndCaching.dto.StudentDto;
import com.abhishek.auditorAwareAndCaching.entities.AdmissionRecord;
import com.abhishek.auditorAwareAndCaching.entities.Student;
import com.abhishek.auditorAwareAndCaching.repository.AdmissionRecordRepository;
import com.abhishek.auditorAwareAndCaching.repository.StudentRepository;
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
