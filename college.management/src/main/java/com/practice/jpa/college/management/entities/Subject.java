package com.practice.jpa.college.management.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Subject extends AuditableEntity {
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


