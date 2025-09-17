package com.practice.jpa.college.management.services;

import com.practice.jpa.college.management.dto.AdmissionRecordDto;
import com.practice.jpa.college.management.dto.AdmissionRecordRequestDto;

public interface AdmissionRecordService {
    AdmissionRecordDto createNewAdmissionRecord(AdmissionRecordRequestDto data) ;
}
