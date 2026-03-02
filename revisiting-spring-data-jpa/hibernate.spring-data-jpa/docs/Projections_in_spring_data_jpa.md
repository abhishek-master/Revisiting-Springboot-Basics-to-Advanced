# Projection in Spring Boot (Spring Data JPA)

## 📌 Overview

**Projection** in Spring Boot (using Spring Data JPA) is a technique that allows you to fetch only selected fields (columns) from a database instead of retrieving the entire entity.

It helps to:

- Improve performance
- Reduce memory usage
- Avoid exposing sensitive data
- Optimize API responses

Projection is primarily supported through **Spring Data JPA**.

---

# 🧠 Why Use Projection?

Consider the following entity:

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;

    private String name;
    private String email;
    private String password;

    // Getters and Setters
}
```

If your application only needs `name` and `email`, retrieving the full entity (including `password`) is unnecessary and inefficient.

Projection allows you to fetch only the required fields.

---

# 📚 Types of Projections

## 1️⃣ Interface-Based Projection (Recommended for Simple Cases)

### Step 1: Create an Interface

```java
public interface UserSummary {
    String getName();
    String getEmail();
}
```

### Step 2: Use It in Repository

```java
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<UserSummary> findByName(String name);
}
```

Spring Data JPA automatically implements the interface.

### ✅ Advantages

- Very simple
- No manual implementation required
- Best for read-only views
- Cleaner and minimal code

---

## 2️⃣ Class-Based Projection (DTO Projection)

Used when you need more control or complex queries.

### Step 1: Create DTO Class

```java
public class UserDTO {

    private String name;
    private String email;

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
}
```

### Step 2: Use JPQL Query

```java
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT new com.example.dto.UserDTO(u.name, u.email) FROM User u")
    List<UserDTO> getAllUsers();
}
```

### ✅ Advantages

- Works well with joins
- Better control over returned data
- Suitable for complex queries
- Explicit and clear mapping

---

## 3️⃣ Dynamic Projection

Allows choosing the projection type at runtime.

### Repository Method

```java
<T> List<T> findByName(String name, Class<T> type);
```

### Usage

```java
List<UserSummary> summary =
        userRepository.findByName("John", UserSummary.class);

List<User> fullEntity =
        userRepository.findByName("John", User.class);
```

### ✅ Advantages

- Highly flexible
- Reusable repository methods
- Useful in generic service layers

---

# 🔍 Closed vs Open Projections

## 🔹 Closed Projection

Maps directly to entity fields.

```java
public interface UserView {
    String getName();
}
```

✔ Faster  
✔ Better performance  
✔ Recommended for most cases

---

## 🔹 Open Projection

Uses Spring Expression Language (SpEL).

```java
import org.springframework.beans.factory.annotation.Value;

public interface UserView {

    @Value("#{target.name + ' - ' + target.email}")
    String getUserInfo();
}
```

⚠ Open projections evaluate expressions at runtime and may impact performance.

---

# 🗄 Projection with Native Query

```java
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT name, email FROM users WHERE name = :name",
           nativeQuery = true)
    List<UserSummary> findByNameNative(@Param("name") String name);
}
```

---

# 🔗 Projection with Relationships Example

Assume:

```java
@Entity
public class Order {

    @Id
    private Long id;

    private Double amount;

    @ManyToOne
    private User user;
}
```

### Projection Interface

```java
public interface OrderView {
    Double getAmount();
    UserInfo getUser();

    interface UserInfo {
        String getName();
        String getEmail();
    }
}
```

Spring automatically handles nested projections.

---

# 🚀 When to Use Projection

Use projection when:

- You only need partial entity data
- You are building REST APIs
- You want to improve performance
- You need to hide sensitive fields
- You are working with large datasets
- You want optimized database queries

---

# ⚡ Performance Benefits

Without Projection:
- Entire entity is fetched
- More memory usage
- Possible exposure of sensitive fields

With Projection:
- Only required columns are selected
- Smaller result set
- Faster query execution
- Better scalability

---

# 🏆 Best Practices

- Prefer **interface-based projection** for simple read operations
- Use **DTO projection** for complex queries or joins
- Avoid open projections in performance-critical systems
- Do not use projections for write operations
- Keep projection interfaces focused and small
- Use dynamic projections when building reusable repository methods

---

# 📌 Summary

Projection in Spring Boot using Spring Data JPA allows you to:

- Retrieve only required columns
- Improve query performance
- Reduce memory consumption
- Prevent exposure of sensitive fields
- Build efficient and scalable APIs

It is a powerful optimization technique and an essential concept when working with large-scale applications.

---

# 🎯 Conclusion

Projection is a simple yet powerful feature of Spring Data JPA that enables:

- Cleaner APIs
- Better database performance
- More secure data handling
- Improved application scalability

Understanding and properly using projections can significantly enhance your application's efficiency.