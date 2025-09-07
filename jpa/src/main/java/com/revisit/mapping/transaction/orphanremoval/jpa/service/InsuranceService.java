package com.revisit.mapping.transaction.orphanremoval.jpa.service;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Insurance;
import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Patient;
import com.revisit.mapping.transaction.orphanremoval.jpa.repository.InsuranceRepository;
import com.revisit.mapping.transaction.orphanremoval.jpa.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;


@RequiredArgsConstructor
@Service
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, BigInteger patientId){
        Patient patient = patientRepository.findById(patientId).orElse(null);
        patient.setInsurance(insurance); //This will make sure that insurance is saved in the DB both sides when transaction
        //is committed.

        insurance.setPatient(patient);//This is optional but say in the further logic we need to use insurance and expect to have
        //patient data in it. It will not save or make any change in DB as its not the owning side of relationship.
        System.out.println(patient);
        return insurance;
    }

}
