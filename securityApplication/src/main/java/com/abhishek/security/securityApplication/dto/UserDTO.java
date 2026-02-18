package com.abhishek.security.securityApplication.dto;

import com.abhishek.security.securityApplication.entities.enums.Permission;
import com.abhishek.security.securityApplication.entities.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Integer id ;
    private String email ;
    private String name ;
    private Set<Role> roles ;
    private Set<Permission> permissions ;
}
