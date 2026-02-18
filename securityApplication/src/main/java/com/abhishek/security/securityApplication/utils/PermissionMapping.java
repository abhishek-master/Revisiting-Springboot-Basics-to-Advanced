package com.abhishek.security.securityApplication.utils;

import com.abhishek.security.securityApplication.entities.enums.Permission;
import com.abhishek.security.securityApplication.entities.enums.Role;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

public class PermissionMapping {
    private static final Map<Role, Set<Permission>> mapex = Map.of(
            Role.USER, Set.of(Permission.POST_VIEW),
            Role.CREATOR, Set.of(Permission.POST_VIEW, Permission.POST_CREATE, Permission.POST_UPDATE),
            Role.ADMIN, Set.of(Permission.POST_VIEW, Permission.POST_CREATE, Permission.POST_UPDATE, Permission.POST_DELETE, Permission.USER_VIEW, Permission.USER_CREATE, Permission.USER_UPDATE, Permission.USER_DELETE)
    );

    public static Set<Permission> getAuthoritiesForRole(Role role) {
        return mapex.getOrDefault(role, Set.of());
    }
}
