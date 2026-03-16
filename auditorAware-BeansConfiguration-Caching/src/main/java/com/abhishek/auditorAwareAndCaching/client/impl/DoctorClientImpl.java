package com.abhishek.auditorAwareAndCaching.client.impl;

import com.abhishek.auditorAwareAndCaching.client.DoctorsClient;
import com.abhishek.auditorAwareAndCaching.dto.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorClientImpl implements DoctorsClient {

    private final RestClient restClient ;

    @Override
    public List<DoctorDto> getAllDoctors() {
        try{
            List<DoctorDto> doctors = restClient.get()
                    .uri("/jpa/getAllDocs")
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<DoctorDto>>() { // we need to pass the type of data we are
                        // going ot handle in the body
                    });
            return doctors;
        }catch(Exception e) {
            throw new RuntimeException(e) ;
        }
    }
    //I will be using Tests to call this
}
