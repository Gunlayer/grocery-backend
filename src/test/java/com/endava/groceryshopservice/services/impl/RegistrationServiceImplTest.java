package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.RegistrationResponseDTO;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.repositories.UserRepository;
import com.endava.groceryshopservice.security.JwtTokenProvider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static com.endava.groceryshopservice.utils.RegistrationReqDtoUtils.REGISTRATION_REQUEST;
import static com.endava.groceryshopservice.utils.TestConstants.USER_TOKEN;
import static com.endava.groceryshopservice.utils.TestConstants.VISITOR_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {

    @Mock
    private JwtTokenProvider tokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidationServiceImpl validationService;

    @Mock
    private ItemServiceImpl itemService;

    @Mock
    private VisitorServiceImpl visitorService;

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Test
    void register_ResponseOk_validData() {
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        ReflectionTestUtils.setField(registrationService, "strength", 12);
        User user = REGISTRATION_REQUEST.toUser();
        Mockito.when(userRepository.findByEmail(REGISTRATION_REQUEST.getEmail())).thenReturn(Optional.empty());
        doNothing().when(visitorService).deleteVisitor(VISITOR_ID);
        Mockito.when(tokenProvider.createToken(user.getEmail(), user.getRole().name())).thenReturn(USER_TOKEN);

        ResponseEntity<RegistrationResponseDTO> response = registrationService.register(REGISTRATION_REQUEST);

        assertAll(
                () -> verify(validationService).testEmailValidation(REGISTRATION_REQUEST.getEmail()),
                () -> verify(validationService).testPasswordValidation(REGISTRATION_REQUEST.getPassword()),
                () -> verify(userRepository).findByEmail(REGISTRATION_REQUEST.getEmail()),
                () -> verify(userRepository).save(userArgumentCaptor.capture()),
                () -> assertThat(userArgumentCaptor.getValue()).isNotNull(),
                () -> assertThat(userArgumentCaptor.getValue().getEmail()).isNotBlank(),
                () -> assertThat(userArgumentCaptor.getValue().getEmail()).isEqualTo(REGISTRATION_REQUEST.getEmail()),
                () -> assertThat(userArgumentCaptor.getValue().getPassword()).isNotBlank(),
                () -> assertThat(response).isNotNull(),
                () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
                () -> assertThat(response.getBody()).isNotNull(),
                () -> assertThat(response.getBody().getEmail()).isNotNull(),
                () -> assertThat(response.getBody().getEmail()).isEqualTo(REGISTRATION_REQUEST.getEmail()),
                () -> assertThat(response.getBody().getToken()).isNotNull(),
                () -> assertThat(response.getBody().getToken()).isEqualTo(USER_TOKEN)
        );
    }

    @Test
    void register_ThrowsException_emailAlreadyExist() {
        Mockito.when(userRepository.findByEmail(REGISTRATION_REQUEST.getEmail())).thenReturn(Optional.of(mock(User.class)));

        assertThatThrownBy(
                () -> registrationService.register(REGISTRATION_REQUEST)
        ).isInstanceOf(AlreadyExistingUserException.class).hasMessage("A user with the same email already exists");
    }
}