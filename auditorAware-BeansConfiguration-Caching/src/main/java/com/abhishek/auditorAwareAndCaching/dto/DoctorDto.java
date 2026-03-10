package com.abhishek.auditorAwareAndCaching.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DoctorDto {
    String specialization ;
    String name ;
    String email ;
    String department ;
}
