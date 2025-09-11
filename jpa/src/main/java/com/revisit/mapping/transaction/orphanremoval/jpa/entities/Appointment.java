package com.revisit.mapping.transaction.orphanremoval.jpa.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    BigInteger id;

    @Column(nullable = false)
    LocalDateTime appointmentTime;

    @Column(length = 500)
    String reason;

    String status;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    Patient patient ; //Owning side of [Patient to Appointment] relationship.

    @ManyToOne
    @JoinColumn
    Doctor doctor;  //Owning side of [Doctor to Appointment] relationship.
}

/*
 * Note that owning side and reverse side has nothing to do with parent entity and child entity.
 * Owning side is the one that is having the FK column, and we have "@JoinColumn"  and the reverse side it the
 * one that is having the "mappedBy" attribute.
 * Like here though the Appointment table has owning side while Doctor and Patient are inverse side.But,
 * Patient is the parent side in Patient to Appointment relationship, because Patient dictates what will happen
 * to the Appointment. Same case with Doctor.
 * Patient is deleted, appointment makes NO sense, patient gets updated with a new appointment, older
 * appointment makes no sense. This is how we need to identify the Parent side and add orphan-removal or cascading
 * accordingly.
 * */
