package com.practice.jpa.college.management;

import com.practice.jpa.college.management.client.impl.DoctorClientImpl;
import com.practice.jpa.college.management.dto.DoctorDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestRestClient {
    @Autowired
    DoctorClientImpl doctorClientService ;

    @Test
    void getAllDoctors () {
        List<DoctorDto> doctorDtoList = doctorClientService.getAllDoctors();
        System.out.println(doctorDtoList.toString());
    }
}
