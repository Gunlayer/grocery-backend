package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.UserInformationDto;
import com.endava.groceryshopservice.entities.user_permission.Role;
import com.endava.groceryshopservice.entities.user_permission.Status;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.repositories.UserRepository;
import com.endava.groceryshopservice.services.UserValidationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.USER_TOKEN;
import static com.endava.groceryshopservice.utils.UserUtils.ADMIN_ONE;
import static com.endava.groceryshopservice.utils.UserUtils.USER_LIST;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidationService userValidationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private User user;

    @Test
    void getAll_listUsers() {
        List<UserInformationDto> users = Stream.of(USER_ONE, ADMIN_ONE).map(User::toUserInformationDto).collect(Collectors.toList());
        Page<User> page = new PageImpl(users);
        Pageable pageable = PageRequest.of(0, 2);

        when(userRepository.findAll(pageable)).thenReturn(page);
        userService.getAll(pageable,"");
        assertThat(userRepository.findAll(pageable).getSize()).isEqualTo(2);
    }

    @Test
    void save_user_user() {
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(user)
        );
    }


    @Test
    void getByEmail_user_correctData() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.of(user));

        User result = userService.getByEmail(USER_EMAIL);

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(user)
        );
    }

    @Test
    void getByEmail_throwsException_incorrectData() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getByEmail(USER_EMAIL))
                .isInstanceOf(InvalidEmailException.class).hasMessage("Not existing email");
    }

    @Test
    void addUser_User_correctData() {
        User expectedUser = User.builder()
                .email(USER_EMAIL)
                .password(USER_ONE.getPassword())
                .role(USER_ONE.getRole())
                .status(USER_ONE.getStatus())
                .build();

        when(userRepository.findByEmail(expectedUser.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User actualUser = userService.addUser(User.toUserInformationDto(expectedUser));

        assertAll(
                () -> assertThat(actualUser).isNotNull(),
                () -> assertThat(actualUser.getEmail()).isEqualTo(expectedUser.getEmail()),
                () -> assertThat(actualUser.getPassword()).isEqualTo(expectedUser.getPassword())
        );
    }

    @Test
    void addUser_throwsException_incorrectData() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.of(USER_ONE));

        assertThatThrownBy(() -> userService.addUser(User.toUserInformationDto(USER_ONE)))
                .isInstanceOf(AlreadyExistingUserException.class).hasMessage("User with the same email already exists");
    }

    @Test
    void editUser_User_correctData() {
        User expectedUser = User.builder()
                .email(USER_EMAIL)
                .password("12345")
                .role(Role.ADMIN)
                .status(Status.BANNED)
                .build();
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.of(expectedUser));
        when(passwordEncoder.encode("12345")).thenReturn(USER_TOKEN);
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User actualUser = userService.editUser(User.toUserInformationDto(expectedUser));

        assertAll(
                () -> assertThat(actualUser).isNotNull(),
                () -> assertThat(actualUser.getEmail()).isEqualTo(expectedUser.getEmail()),
                () -> assertThat(actualUser.getPassword()).isEqualTo(USER_TOKEN),
                () -> assertThat(actualUser.getStatus()).isEqualTo(Status.BANNED),
                () -> assertThat(actualUser.getRole()).isEqualTo(Role.ADMIN)
        );
    }

    @Test
    void addEdit_throwsException_incorrectData() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.editUser(User.toUserInformationDto(USER_ONE)))
                .isInstanceOf(InvalidEmailException.class).hasMessage("Not existing email");
    }

    @Test
    void deleteUser_User_correctData() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.of(USER_ONE));

        User actualUser = userService.deleteUser(USER_EMAIL);

        assertAll(
                () -> assertThat(actualUser).isNotNull(),
                () -> assertThat(actualUser.getEmail()).isEqualTo(USER_EMAIL),
                () -> assertThat(actualUser.getPassword()).isEqualTo(USER_ONE.getPassword())
        );
    }

    @Test
    void deleteUser_throwsException_incorrectData() {
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.deleteUser(USER_EMAIL))
                .isInstanceOf(InvalidEmailException.class).hasMessage("Not existing email");
    }

    @Test
    void shouldFindNewRegisteredUsers(){
        when(userRepository.findByRegistrationDateAfter(LocalDate.now().minusDays(7))).thenReturn(USER_LIST);

        List<User> expected = userService.findNewRegisteredUsers();

        assertEquals(expected, USER_LIST);
    }
}