package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO {
    private Integer id ;
    private Integer age;
    private String name;
    private String address;
    private Boolean isActive ;

}
