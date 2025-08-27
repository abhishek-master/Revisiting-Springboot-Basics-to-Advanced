package com.codingshuttle.abhishek.week1Introduction.introductionToSpringBoot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@Getter
@Setter
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id ;
    private Integer age;
    private String name;
    private String address;
    private Boolean isActive ;

    @Override
    public String toString() {
        return " { " + "id: " + id + " age: " + age + " name: " + name + " address: " + address + " isActive: " + isActive + " }";
    }
}
