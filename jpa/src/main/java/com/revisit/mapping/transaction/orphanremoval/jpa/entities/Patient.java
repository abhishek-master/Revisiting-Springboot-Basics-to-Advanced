package com.revisit.mapping.transaction.orphanremoval.jpa.entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigInteger id ;

    private String name;

    private String gender;

    private LocalDateTime birthDate ;

    private String email;

    private String bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt ;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(unique=true)
    @ToString.Exclude
    private Insurance insurance ; //owning side for [Patient to Insurance Relationship]

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<>();  //Inverse side for [Patient to Appointment Relationship]

}
/*
 * About @JoinColumn:
 * -------------------------
 * - Always added on the "owning side" of a relationship (the table that contains the foreign key).
 * - If not specified, JPA uses a default naming convention:
 *   "<related_entity_name>_<primary_key_name>".
 *   Example: For Patient → Insurance, it will be "insurance_id".
 * - Additional properties like "unique", "nullable" etc. can be specified here.
 */

/*
 * Understanding Cascade in JPA:
 * -----------------------------------
 * Think of cascade like a waterfall: an action taken on the parent
 * also flows down to its children automatically.
 *
 * Types of Cascade:
 * - PERSIST: If we save the parent, the child automatically gets saved.
 *   Example: If we call patient.setInsurance(insurance) and then persist the patient,
 *   the insurance will also be persisted without calling save() separately.
 *
 * - MERGE: If the parent is updated, the related child entities are updated as well.
 *
 * - REMOVE: If the parent is deleted, its child entities are also deleted.
 *   Example: If a Patient has 100 Appointments and the patient is removed,
 *   all 100 appointments are also removed.
 *
 * - ALL: Shortcut that applies all of the above (PERSIST, MERGE, REMOVE).
 */

/*
 * Cascade means JHARNA in english so just relate from there like the water fall from 1000ft height it strikes to
 * a layered rocks and then again falls on another layered rock and like that it reaches the bottom.
 * Similarly, we define the cascade for the relationship tables. When an effect takes place on the Parent/Owning
 * side then will the effect take place on the child/inverse side.
 * Like say Insurance is created and then assigned to the Patient, and we are just doing,
 * patient.setInsurance(insurance) we are marking just patient dirty so ideally persistence context will get it saved
 * when transaction gets over, NOTE THAT INSURANCE will not be saved but. We want insurance to be saved as well.
 * Therefore, we have added Cascade.type=persist this will make the child side saved as well.
 * cascadeType.merge === Does same thing on update operations, if Parent side is updated, child side gets updated
 * as well in the DB.
 * cascadeType.REMOVE === Does same thing, if parent  side gets deleted, child side as well gets deleted from DB.
 * For example if we have 100 appointments to the Patient and that Patient got deleted. All the appointments, yes all
 * the 100 will get deleted.
 * cascadeType.ALL === Makes sure cascading is followed for all PERSIST, MERGE and REMOVE operations.
 *
 * */
