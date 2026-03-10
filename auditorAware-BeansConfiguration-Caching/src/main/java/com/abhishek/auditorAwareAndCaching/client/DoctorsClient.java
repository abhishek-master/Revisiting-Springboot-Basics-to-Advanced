package com.abhishek.auditorAwareAndCaching.client;

import com.abhishek.auditorAwareAndCaching.dto.DoctorDto;

import java.util.List;

public interface DoctorsClient {
    List<DoctorDto> getAllDoctors () ;
}
