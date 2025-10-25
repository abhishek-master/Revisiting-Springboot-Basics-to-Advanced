package com.revisiting.jpa.hibernate.spring_data_jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

/*
* **********This entity along with all the services and controller is made to understand projection in SpringBoot JPA*******
It is not required to give the @Column annotation on every field in a JPA entity. By default, JPA will map fields to columns with the same name in the database table following a naming strategy.

Details:
If you omit @Column, JPA automatically assumes the column name matches the field/property name.

Use @Column when you want to customize the mapping, such as specifying:
- A different column name (name attribute)
- Column constraints like nullable, unique, length
- Precision and scale for numeric columns
- Insertable/updatable options
* */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id ;

    @Column
    String name ;

    @Column
    Integer numberOfClasses;

    @Column
    Integer numberOfStudents ;

    @Column
    Integer numberOfProfessors ;

    @Column
    String hod ;

    @CreationTimestamp
    LocalDateTime createdAt ;

    @UpdateTimestamp
    LocalDateTime updatedAt ;

}
