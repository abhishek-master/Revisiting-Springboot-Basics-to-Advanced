package com.revisiting.jpa.hibernate.spring_data_jpa.dto;


/*
* Say we do not need the data for manipulationa and just need to view data only then we can create these
* kind of interfaces and we can use them to get just required data from the JPA queries by setting the return type as
* these interfaces.
* */
public interface IDepartmentDto {
    Long getId();
    String getName();
    String getHod();
}
