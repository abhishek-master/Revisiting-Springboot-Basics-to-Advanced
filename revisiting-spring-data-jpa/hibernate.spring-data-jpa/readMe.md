# JPA and Spring Transaction Management - Detailed Explanation

This document elaborates key concepts regarding JPA persistence context, entity states (detached, transient), transactional behavior in Spring Boot, and transaction propagation, including `REQUIRES_NEW`.

## Persistence Context in JPA

- The **persistence context** is a set of entity instances in which for any persistent entity identity, there is a unique entity instance.
- It acts like a first-level cache where JPA tracks changes for entities that are **managed** by it.
- Changes to managed entities are **automatically detected (dirty checking)** and flushed to the database when the transaction commits.
- The persistence context lifetime is typically **scoped to a transaction** in Spring (when using `@Transactional`).

---

## Entity States in JPA

### Managed (Persistent) Entities
- Entities currently tracked and managed by the persistence context.
- Changes are synchronized to the database on transaction commit.

### Detached Entities
- Previously managed entities whose persistence context is closed, cleared, or the entity was explicitly detached.
- Changes to detached entities are **not** tracked or saved until they are merged back.
- Useful in long-running transactions or when transferring entities between layers or different persistence contexts.

### Transient Entities
- New entities that are not yet persisted and are not part of any persistence context.
- Using the Java `transient` keyword or JPA’s `@Transient` annotation can mark fields that are **not persisted to the database**.
- Helps store temporary or calculated data within the entity class, which is not saved.

---

## Spring @Transactional and Persistence Context

- The persistence context is **created and associated with a transaction**.
- The `@Transactional` annotation starts (or joins) a transaction and opens a persistence context.
- All entity operations within a `@Transactional` method participate in this context and transaction scope.
- Without `@Transactional`, changes to entities are not automatically tracked or saved, since no persistence context lifecycle is bound.

---

## Transaction Propagation in Spring

### Default Propagation: `REQUIRED`

- Joins an existing transaction if present or creates a new one if not.
- All transactional methods share the **same persistence context and transaction**.
- Nested method calls within the same transaction share context and commit their changes atomically at the end of the outer transaction.

### `REQUIRES_NEW` Propagation

- Suspends the current transaction and starts a **new, independent transaction**.
- Changes made inside `REQUIRES_NEW` commit or rollback independently of the outer transaction.
- Useful for tasks that should commit regardless of the outer transaction outcome (e.g., audit logging).

---

## Nested Transactions and Entity Behavior Example
```Java
class A {
    @Transactional
    public List<Something> doSomething() {
        temp t = new temp();
        t.setProperty();
        t.set(); // update entity

        temp modified_t = doSomethingOtherClass(t);
        modified_t.flush(); // flush changes in outer transaction
    }
}

class B {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public temp doSomethingOtherClass(temp t) {
        // modify and save entity in a new transaction
    }
}
```

### What happens?

- `doSomething()` starts the outer transaction.
- Calls `doSomethingOtherClass()` which starts a **new inner transaction** due to `REQUIRES_NEW`.
- Inner method changes and commits independently **before** outer transaction commits.
- Outer transaction resumes after inner completes and can still commit or roll back its own changes.
- If the outer transaction rolls back, inner transaction changes **remain** committed.

---

## Scenario: Deleting Entity in Inner Transaction

If `doSomethingOtherClass()` deletes entity `t`:

- Deletion happens and commits immediately in the inner transaction.
- Outer transaction suspends during this inner transaction and on resuming, the entity `t` no longer exists in the database.
- If outer transaction tries to operate on `t` after this, it may throw exceptions (e.g., `EntityNotFoundException`).
- Outer rollback does not undo the inner transaction deletion.

---

## Summary

| Concept                     | Description                                                        |
|-----------------------------|--------------------------------------------------------------------|
| Persistence Context         | Manages entity state, scoped to a transaction in Spring.          |
| Managed Entity              | Tracked and persisted automatically in a persistence context.      |
| Detached Entity             | Not tracked, requires merging to persist changes.                   |
| Transient Entity / Field    | Not persisted to the DB; marked with `@Transient` or `transient`.  |
| `@Transactional`            | Starts or joins transaction & opens persistence context.           |
| Propagation `REQUIRED`      | Joins existing transaction or creates new if none present.         |
| Propagation `REQUIRES_NEW`  | Suspends outer transaction, starts new independent transaction.    |
| Nested Transactions         | Inner `REQUIRES_NEW` commits independently; outer transaction resumes after. |

---

## References

- [Baeldung: Hibernate Dirty Checking](https://www.baeldung.com/java-hibernate-entity-dirty-check)[web:30]
- [Spring Framework Transaction Propagation Reference](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction-declarative-annotations-propagation)[web:51]
- [StackOverflow: Spring REQUIRED vs REQUIRES_NEW](https://stackoverflow.com/questions/13051204/spring-transaction-required-vs-requires-new-rollback-transaction)[web:60]
- [Hibernate Entity States](https://www.objectdb.com/java/jpa/persistence/detach)[web:5]
- [Baeldung: @Transient in JPA](https://www.baeldung.com/jpa-transient-ignore-field)[web:17]


---
