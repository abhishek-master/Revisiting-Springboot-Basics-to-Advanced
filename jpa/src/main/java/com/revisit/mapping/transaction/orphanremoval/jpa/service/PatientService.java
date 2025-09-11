package com.revisit.mapping.transaction.orphanremoval.jpa.service;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Appointment;
import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Insurance;
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

    @Transactional
    public void getPatient(BigInteger id) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        System.out.println(patient);
        System.out.println(patient.getAppointments());
    }

    @Transactional
    public void updatePatientAppointment(BigInteger patientId, Appointment appointment){

    }

    @Transactional
    public void updatePatientInsurance(BigInteger patientId, Insurance insurance){
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        System.out.println("Patient ::::: " + patient);
        System.out.println("Insurance ::::::: " + insurance);
    }
}

/*
*
* */
