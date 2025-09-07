# Explanation for ManyToMany Annotation on Doctor Side Only

## ManyToMany Relationship Basics

- A **ManyToMany relationship** between two entities (e.g., Doctor and Department) means each Doctor can be assigned to multiple Departments, and each Department can have multiple Doctors.
- To represent this in a relational database, a **join table** (e.g., DOCTOR_DEPARTMENT) is used to link primary keys of both entities.

## Owning Side vs. Inverse Side

- In JPA/Hibernate, **every bidirectional ManyToMany association has two sides**:
    - **Owning side:** Manages the relationship and the join table configuration.
    - **Inverse side (non-owning):** Uses the `mappedBy` attribute to specify that the other side is the owner.

- The owning side is responsible for updates to the join table.
- The inverse side simply reflects the association and does not manage the join table.

> Example from official JPA docs:  
> On owning side:
> ```
> @ManyToMany
> @JoinTable(name = "DOCTOR_DEPARTMENT",
>     joinColumns = @JoinColumn(name = "doctor_id"),
>     inverseJoinColumns = @JoinColumn(name = "department_id"))
> private Set<Department> departments;
> ```
> On inverse side:
> ```
> @ManyToMany(mappedBy = "departments")
> private Set<Doctor> doctors;
> ```

## Why Choose Doctor as the Owning Side?

- The owning side is often selected based on domain logic: typically, the side that **controls or manages the relationship** is chosen as the owner.
- In a Hospital Management System, Doctor assignments to departments are usually managed from the Doctor's perspective, so the Doctor entity is the owning side.
- This avoids duplication in database updates and reduces complexity, ensuring a single source of truth for relationship management.

## Difference from OneToMany / ManyToOne

- In OneToMany / ManyToOne relationships, the foreign key resides on the “many” side (ManyToOne), which is naturally the owning side.
- The “one” side usually has a `mappedBy` attribute and does not own the foreign key.
- For ManyToMany, there is no inherent “many” or “one” side, so one side must be explicitly chosen as owning.

---

# Summary

| Concept                   | Explanation                                                    |
|---------------------------|----------------------------------------------------------------|
| ManyToMany owning side    | The side that manages the join table and relationship updates |
| Inverse (non-owning) side | The side with `mappedBy` referencing the owner                 |
| Doctor as owner           | Chosen due to domain logic around managing department assigns |
| Department as inverse     | Mirrors the Doctor side, no join table management              |
| OneToMany / ManyToOne     | Foreign key always on "many" side, which is the owning side    |

---

# References

- JPA `@ManyToMany` specification (Oracle Java EE Documentation)  
  https://docs.oracle.com/javaee/7/api/javax/persistence/ManyToMany.html
- Baeldung: Many-To-Many Relationship in JPA  
  https://www.baeldung.com/jpa-many-to-many
- Hibernate Many-to-Many Best Practices  
  https://thorben-janssen.com/best-practices-for-many-to-many-associations-with-hibernate-and-jpa/  
