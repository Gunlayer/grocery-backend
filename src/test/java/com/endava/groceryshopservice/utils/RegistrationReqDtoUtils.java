package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.dto.UserRequestDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.endava.groceryshopservice.utils.TestConstants.PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationReqDtoUtils {
    public final static UserRequestDTO REGISTRATION_REQUEST =
            new UserRequestDTO(USER_EMAIL, PASSWORD, List.of());
}
