package com.revisit.mapping.transaction.orphanremoval.jpa.service;

import com.revisit.mapping.transaction.orphanremoval.jpa.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository ;

    @Transactional
    public void deleteDoctorById(BigInteger doctorId){
        doctorRepository.deleteById(doctorId);

    }


}
