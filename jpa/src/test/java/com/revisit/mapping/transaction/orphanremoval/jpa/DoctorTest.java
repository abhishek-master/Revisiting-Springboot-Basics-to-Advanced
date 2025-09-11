package com.revisit.mapping.transaction.orphanremoval.jpa;

import com.revisit.mapping.transaction.orphanremoval.jpa.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest
public class DoctorTest {

    @Autowired
    private DoctorService doctorService ;

    @Test
    public void testDeleteDoctor(){
        doctorService.deleteDoctorById(BigInteger.valueOf(1));
    }
}
