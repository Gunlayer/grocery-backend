package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.user_permission.Status;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.entities.user_permission.Role.USER;
import static com.endava.groceryshopservice.utils.TestConstants.ID_ONE;
import static com.endava.groceryshopservice.utils.TestConstants.PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtils {
    public static final User USER_ONE = User.builder()
            .id(ID_ONE)
            .email(USER_EMAIL)
            .password(PASSWORD)
            .role(USER)
            .status(Status.ACTIVE)
            .build();
}
