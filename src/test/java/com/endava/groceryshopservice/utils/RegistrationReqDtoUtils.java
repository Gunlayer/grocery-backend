package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.dto.UserRequestDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.USER_PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.VISITOR_ID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationReqDtoUtils {
    public static final UserRequestDTO REGISTRATION_REQUEST = UserRequestDTO.builder()
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .visitorId(VISITOR_ID)
            .build();
}
