package com.revisit.mapping.transaction.orphanremoval.jpa.service;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Patient;
import com.revisit.mapping.transaction.orphanremoval.jpa.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@Service
public class PatientService {
    private final PatientRepository patientRepository ;

    PatientService (PatientRepository patientRepository){
        this.patientRepository = patientRepository ;
    }

    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    @Transactional
    public void removePatient(BigInteger id){
         patientRepository.deleteById(id);

    }
}
