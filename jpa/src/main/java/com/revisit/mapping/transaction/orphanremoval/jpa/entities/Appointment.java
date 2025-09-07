package com.revisit.mapping.transaction.orphanremoval.jpa.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    Patient patient ; //Owning side of [Patient to Appointment] relationship.

    @ManyToOne
    @JoinColumn(nullable = false)
    Doctor doctor;  //Owning side of [Doctor to Appointment] relationship.
}
