package com.revisit.mapping.transaction.orphanremoval.jpa;

import com.revisit.mapping.transaction.orphanremoval.jpa.entities.Insurance;
import com.revisit.mapping.transaction.orphanremoval.jpa.service.InsuranceService;
import com.revisit.mapping.transaction.orphanremoval.jpa.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService ;
    @Autowired
    private PatientService patientService;

    @Test
    public void testAssignInsuranceToPatient(){
        Insurance insurance = Insurance.builder().provider("HDFC")
                .policyNumber("HDFC_oP#rwa)R3r3r342")
                .validUntil(LocalDate.of(2029, 1, 1))
                .build();
        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance, BigInteger.valueOf(1L));
        System.out.println(updatedInsurance);
    }
}
