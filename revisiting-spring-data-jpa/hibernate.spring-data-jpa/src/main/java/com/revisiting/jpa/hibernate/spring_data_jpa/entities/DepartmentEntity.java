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
