package com.practice.jpa.college.management.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/*
It will put all the fields in the classes
that inherit it. In our case all the tables(Entities)
*/
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false) //To enforce this column is not updated in any case
    private LocalDateTime createdAt ;

    @LastModifiedDate
    private LocalDateTime updatedDate ;

    @CreatedBy
    private String createdBy ;

    @LastModifiedBy
    private String updatedBy ;
}
