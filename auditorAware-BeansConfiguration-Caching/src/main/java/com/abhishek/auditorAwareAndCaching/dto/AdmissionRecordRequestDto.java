package com.abhishek.auditorAwareAndCaching.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionRecordRequestDto {
    Integer fees ;
    Integer studentId;
}
