package com.revisit.mapping.transaction.orphanremoval.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger Id ;

    private String policyNumber;

    private String provider;

    private LocalDate validUntil;

    private LocalDateTime createdAt;

    @OneToOne(mappedBy="insurance")
    private Patient patient; //inverse side
}
/*
 * ByDirectional Mapping :
 * Like we have "insurance" in this entity and "patient" in the Insurance entity so both are able to access
 * each other, through each of their instances. This is called bidirectional mapping.
 * But then we will face the issue of loosing single SOURCE OF TRUTH, we will have insurance_id as a FK in
 * patient table and patient_id as FK in the Insurance table. Which we do not want. We need to keep one
 * side as owning side and other side as inverse side.
 * To resolve this issue we use the "mappedBy".
 * mappedBy property in the @OneToOne annotation helps us to tell Hibernate which is owning side
 * and which is inverse side.
 * We use mappedBy to the table which is the reverseSide to let the Hibernate know that this property
 * can be set from this mapped column from the related table(in this case Patient is the owning side and
 * Insurance is the inverse side).
 * */