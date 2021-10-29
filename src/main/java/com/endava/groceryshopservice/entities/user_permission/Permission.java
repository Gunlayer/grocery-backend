package com.endava.groceryshopservice.entities.user_permission;

public enum Permission {
    USERS_READ("users:read"),
    USERS_ADD_IN_BAG("users:addInBag"),
    USERS_WRITE("users:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}