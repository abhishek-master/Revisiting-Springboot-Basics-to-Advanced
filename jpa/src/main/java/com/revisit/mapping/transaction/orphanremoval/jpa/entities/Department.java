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
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    BigInteger id ;

    String name;

    LocalDateTime createdAt ;

    @OneToOne
    @JoinColumn(nullable = false)
    Doctor headDoctor;

    @ManyToMany
    Set<Doctor> doctors = new HashSet<>();

    /*
     * Hibernate creates a Join_Table for Many to Many mapping, just a column would not solve the purpose.
     * With both columns.
     * */

}
