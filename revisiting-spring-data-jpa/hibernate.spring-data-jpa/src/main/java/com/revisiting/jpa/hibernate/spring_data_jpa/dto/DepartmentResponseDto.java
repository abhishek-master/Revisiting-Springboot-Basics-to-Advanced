package com.revisiting.jpa.hibernate.spring_data_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Say we need to get only limited rows to be send in the API response then we create these ResponseDTO and map
 * only the required fields from the ton of data recieved from the JPA impl.
 * Following this pattern we also have RequestDTOs for the main DTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDto {
    private Long id;
    private String name;
}
