package com.revisit.mapping.transaction.orphanremoval.jpa;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Patient;
import com.revisit.mapping.transaction.orphanremoval.jpa.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDateTime;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientService patientService ;

    @Test
    public void testCreatePatient(){
        Patient patient = Patient.builder().email("abhishek@hotmail.com")
                .name("Abhishek")
                .gender("Male")
                .bloodGroup("A+")
                .birthDate(LocalDateTime.of(1999, 1, 1, 12, 12, 12)).build();

        System.out.println(patientService.createPatient(patient));
    }

    @Test
    public void testRemovePatient(){
        patientService.removePatient(BigInteger.valueOf(1));
    }


}
