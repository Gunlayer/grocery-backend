package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.dto.RegistrationRequestDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.endava.groceryshopservice.utils.TestConstants.PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationReqDtoUtils {
    public final static RegistrationRequestDTO REGISTRATION_REQUEST =
            new RegistrationRequestDTO(USER_EMAIL, PASSWORD);
}
