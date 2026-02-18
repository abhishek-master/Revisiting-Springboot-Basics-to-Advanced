package com.abhishek.security.securityApplication.dto;

import com.abhishek.security.securityApplication.entities.enums.Permission;
import com.abhishek.security.securityApplication.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {
    private String email ;
    private String name;
    private String password ;
    private Set<Role> roles ;
    private Set<Permission> permissions ;
}
