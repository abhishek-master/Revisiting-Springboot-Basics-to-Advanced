package com.revisiting.jpa.hibernate.spring_data_jpa.dto;

import lombok.Data;

/*
* Main DTO class for the entity, to make Service interact with this rather than the Entity classes.
* */
@Data
public class DepartmentDto {
    Long id ;

    String name ;

    Integer numberOfClasses;

    Integer numberOfStudents ;

    Integer numberOfProfessors ;

    String hod ;
}
