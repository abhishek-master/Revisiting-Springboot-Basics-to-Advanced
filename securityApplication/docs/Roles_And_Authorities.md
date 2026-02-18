# Spring Security: Roles vs Authorities

In Spring Security, both **Roles** and **Authorities** are technically represented by the `GrantedAuthority` interface. However, they serve different conceptual purposes and follow different naming rules.

## 1. Key Differences

| Feature | Role | Authority (Permission) |
| :--- | :--- | :--- |
| **Concept** | A high-level group (e.g., ADMIN, USER). | A granular permission (e.g., READ, WRITE). |
| **Prefix** | Must start with `ROLE_` (e.g., `ROLE_ADMIN`). | No prefix required (e.g., `user:read`). |
| **Check Method** | `hasRole("ADMIN")` | `hasAuthority("user:read")` |
| **Logic** | Spring automatically adds `ROLE_` prefix. | Spring checks for an exact string match. |

---

## 2. Code Implementation

### UserDetails Setup
When building your `UserDetails` object, you map both to a list of `SimpleGrantedAuthority`.

```
List<GrantedAuthority> authorities = new ArrayList<>();

// Adding a Role (MUST have ROLE_ prefix)
authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

// Adding an Authority (No prefix needed)
authorities.add(new SimpleGrantedAuthority("OP_DELETE_USER"));

return new User(username, password, authorities);
```

### Authorization in SecurityFilterChain
You can restrict access in your [SecurityFilterChain](https://docs.spring.io) configuration:

```
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
        // Automatically checks for "ROLE_ADMIN"
        .requestMatchers("/admin/**").hasRole("ADMIN") 
        
        // Checks for the exact string "OP_DELETE_USER"
        .requestMatchers("/delete/**").hasAuthority("OP_DELETE_USER") 
        .anyRequest().authenticated()
    );
    return http.build();
}
```

### Method Level Security
Use [@PreAuthorize](
Use code with caution.

markdown
https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html
Use code with caution.

markdown
) to secure specific service methods:

```
// Checks for ROLE_ADMIN
@PreAuthorize("hasRole('ADMIN')")
public void deleteEverything() { ... }

// Checks for specific authority
@PreAuthorize("hasAuthority('OP_DELETE_USER')")
public void removeUser(Long id) { ... }
```

---

## 3. Best Practice: Role Hierarchy
Instead of assigning 20 authorities to every admin, use a `RoleHierarchy` to let `ROLE_ADMIN` inherit all `ROLE_USER` permissions.

```
@Bean
static RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
    hierarchy.setHierarchy("ROLE_ADMIN > ROLE_STAFF \n ROLE_STAFF > ROLE_USER");
    return hierarchy;
}