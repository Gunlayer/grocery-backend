package com.endava.groceryshopservice.entities.user_permission;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Role {
    USER(Stream.of(Permission.USERS_READ, Permission.USERS_ADD_IN_BAG, Permission.USERS_ADD_REVIEW).collect(Collectors.toSet())),
    ADMIN(Stream.of(Permission.USERS_READ, Permission.USERS_WRITE, Permission.USERS_ADD_IN_BAG).collect(Collectors.toSet()));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}