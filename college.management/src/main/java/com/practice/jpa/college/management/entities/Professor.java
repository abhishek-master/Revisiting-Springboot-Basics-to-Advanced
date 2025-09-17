package com.practice.jpa.college.management.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    String title ;

    @OneToMany(mappedBy = "professor")
    @ToString.Exclude
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();
}
