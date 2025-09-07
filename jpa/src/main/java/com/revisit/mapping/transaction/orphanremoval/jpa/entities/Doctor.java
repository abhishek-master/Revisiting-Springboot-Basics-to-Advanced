package com.revisit.mapping.transaction.orphanremoval.jpa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    BigInteger id ;

    String name ;

    String specialization ;

    String email;

    LocalDateTime createdAt ;

    @OneToMany(mappedBy = "doctor")
    Set<Appointment> appointments = new HashSet<>();

    @OneToOne(mappedBy = "headDoctor")
    Department department ;

}
