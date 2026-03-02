package com.abhishek.security.securityApplication.entities;

import com.abhishek.security.securityApplication.entities.enums.Permission;
import com.abhishek.security.securityApplication.entities.enums.Role;
import com.abhishek.security.securityApplication.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(unique = true)
    private String email ;
    private String password ;
    private String name ;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    private Set<Permission> permissions ;
//    Commented out these permissions because we will be using Mapped permission with roles (Check out utils > permissionMapping)


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet());

        // Add explicit permissions assigned to the user
//        authorities.addAll(permissions.stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.name()))
//                .collect(Collectors.toSet()));

        // Optionally: Add permissions based on roles from PermissionMapping
        roles.forEach(role -> {
            Set<Permission> rolePermissions = PermissionMapping.getAuthoritiesForRole(role);
            authorities.addAll(rolePermissions.stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                    .collect(Collectors.toList()));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email ; //Let's assume we have email as username itself
    }
}

/*
* Here's Example for authorities :
* Spring Security Gives us Roles and Authorities. Main business use of Authorities are nothing but what actions can entity perform i.e. what authorities to a User is reqiured to perform a certain action which is execute that specific endpoint. See the below code snippet from the SpringSecurityFilterChain Initialization :
httpSecurity.
           authorizeHttpRequests(matcherRegistry -> matcherRegistry
                                 .requestMatchers("/admin/**").hasRole("ADMIN")
                                 .requestMatchers(HttpMethod.GET, "/wallet").hasAnyRole("RIDER", "DRIVER")
                                 .(HttpMethod.POST, "/wallet").hasAuthority("WALLET_CREATE")
                                 .requestMatchers("/auth/**", "/error/*")permitAll()
                                 .anyRequest().authenticated())
                                 ....................
* */
