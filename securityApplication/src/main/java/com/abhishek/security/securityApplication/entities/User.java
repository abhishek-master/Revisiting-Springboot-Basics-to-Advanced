package com.abhishek.security.securityApplication.entities;

import com.abhishek.security.securityApplication.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
        Authorities means what kind of activities can this user can perform, for now we are just
        going to store all kind of roles here.
        Note that we are appending "ROLES_" prefix before roles. In Spring SSecurity we follow this conventions as
        Roles and Authority are pretty similar.
        Go to the JWT Auth Filter and see how these authorities are used.
        * */
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet());
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
