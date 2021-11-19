package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.exceptions.NotSuitablePasswordException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class RegistrationValidationServiceImplTest {
    @InjectMocks
    private RegistrationValidationServiceImpl validationService;

    @Test
    void testEmailValidation_admit_correctData() {
        assertThatCode(() ->
                validationService.testEmailValidation("stefan_cel-mare.si0sfint@its.endava.com")
        ).doesNotThrowAnyException();
    }

    @Test
    void testEmailValidation_throwsException_incorrectData() {
        assertAll(
                () -> assertThatThrownBy(() ->
                        validationService.testEmailValidation(null)
                ).isInstanceOf(InvalidEmailException.class).hasMessage("Email is not valid."),
                () -> assertThatThrownBy(() ->
                        validationService.testEmailValidation("")
                ).isInstanceOf(InvalidEmailException.class).hasMessage("Email is not valid."),
                () -> assertThatThrownBy(() ->
                        validationService.testEmailValidation("to_long_domain_name@system.daac.md")
                ).isInstanceOf(InvalidEmailException.class).hasMessage("Email is not valid."),
                () -> assertThatThrownBy(() ->
                        validationService.testEmailValidation("to_short_domain_name@daac.m")
                ).isInstanceOf(InvalidEmailException.class).hasMessage("Email is not valid."),
                () -> assertThatThrownBy(() ->
                        validationService.testEmailValidation(".test@daac.md")
                ).isInstanceOf(InvalidEmailException.class).hasMessage("Email is not valid.")
        );
    }

    @Test
    void testPasswordValidation_admit_correctData() {
        assertAll(
                () -> assertThatCode(() ->
                        validationService.testPasswordValidation("Qwerty123")
                ).doesNotThrowAnyException(),
                () -> assertThatCode(() ->
                        validationService.testPasswordValidation("123Qwerty")
                ).doesNotThrowAnyException(),
                () -> assertThatCode(() ->
                        validationService.testPasswordValidation("qwerty")
                ).doesNotThrowAnyException(),
                () -> assertThatCode(() ->
                        validationService.testPasswordValidation("1234567890")
                ).doesNotThrowAnyException()
        );
    }

    @Test
    void testPasswordValidation_throwsException_incorrectData() {
        assertAll(
                () -> assertThatThrownBy(() ->
                        validationService.testPasswordValidation(null)
                ).isInstanceOf(NotSuitablePasswordException.class).hasMessage("Password is not valid."),
                () -> assertThatThrownBy(() ->
                        validationService.testPasswordValidation("")
                ).isInstanceOf(NotSuitablePasswordException.class).hasMessage("Password is not valid."),
                () -> assertThatThrownBy(() ->
                        validationService.testPasswordValidation("oleg.ciornei")
                ).isInstanceOf(NotSuitablePasswordException.class).hasMessage("Password is not valid."),
                () -> assertThatThrownBy(() ->
                        validationService.testPasswordValidation("ZAQ!XSW@CDE#")
                ).isInstanceOf(NotSuitablePasswordException.class).hasMessage("Password is not valid.")
        );
    }

}