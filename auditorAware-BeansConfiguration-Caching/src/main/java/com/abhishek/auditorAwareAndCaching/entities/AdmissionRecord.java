package com.abhishek.auditorAwareAndCaching.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class AdmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private Integer fees ;

    @OneToOne(mappedBy = "admissionRecord", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private Student student;
}
