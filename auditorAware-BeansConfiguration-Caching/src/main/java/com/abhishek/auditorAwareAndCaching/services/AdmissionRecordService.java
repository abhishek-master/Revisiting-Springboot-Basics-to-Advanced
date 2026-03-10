package com.abhishek.auditorAwareAndCaching.services;

import com.abhishek.auditorAwareAndCaching.dto.AdmissionRecordDto;
import com.abhishek.auditorAwareAndCaching.dto.AdmissionRecordRequestDto;

public interface AdmissionRecordService {
    AdmissionRecordDto createNewAdmissionRecord(AdmissionRecordRequestDto data) ;
}
