package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.user_permission.Status;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.entities.user_permission.Role.ADMIN;
import static com.endava.groceryshopservice.entities.user_permission.Role.USER;
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.USER_PASSWORD;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtils {
    public static final User USER_ONE = User.builder()
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .role(USER)
            .status(Status.ACTIVE)
            .build();
    public static final User ADMIN_ONE = User.builder()
            .email(ADMIN_EMAIL)
            .password(ADMIN_PASSWORD)
            .role(ADMIN)
            .status(Status.ACTIVE)
            .build();
}
