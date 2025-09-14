package com.practice.jpa.college.management.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private String title ;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @ToString.Exclude
    private Professor professor  ;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable
    private List<Student> students = new ArrayList<>() ;
}


