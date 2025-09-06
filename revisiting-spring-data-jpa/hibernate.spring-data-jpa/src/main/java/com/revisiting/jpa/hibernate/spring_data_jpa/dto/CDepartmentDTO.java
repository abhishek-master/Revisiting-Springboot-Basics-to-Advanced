package com.revisiting.jpa.hibernate.spring_data_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Concrete DTO class in case we need to just need these data and not all the ton of data from the JPQL query we
* can make this as the return type of that repository method. And since this is a concrete class we can further modify
* the data of this.
* It just makes BE servers more fast and scalable as we dont need to fetch whole data to different parts of the
* applications.
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CDepartmentDTO {
    Long id ;
    String name ;
    String hod ;
}
