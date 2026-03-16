package com.abhishek.auditorAwareAndCaching.dto;

import lombok.*;

import java.util.List;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProfessorsResponseDto {
    String title ;
    List<String> subject ;
}
