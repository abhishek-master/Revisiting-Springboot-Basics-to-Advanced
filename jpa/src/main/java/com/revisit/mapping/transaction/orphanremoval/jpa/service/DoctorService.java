package com.revisit.mapping.transaction.orphanremoval.jpa.service;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Doctor;
import com.revisit.mapping.transaction.orphanremoval.jpa.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository ;

    @Transactional
    public void deleteDoctorById(BigInteger doctorId){
        doctorRepository.deleteById(doctorId);

    }

    /*
    * Just an Endpoint created to understand Rest-Client
    * */
    public List<Map<String, String>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll(Sort.by(Sort.Order.asc("name")));

        return doctors.stream().
                map(doctor -> Map.of("name", doctor.getName(), "email", doctor.getEmail(), "specialization", doctor.getSpecialization(), "department", doctor.getDepartment() != null ? doctor.getDepartment().getName() : "")).
                toList();
    }
}
