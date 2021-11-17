package com.endava.groceryshopservice.services.impl;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private User user;

    @Test
    void getAll_listUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(mock(User.class), mock(User.class)));

        List<User> result = userService.findAll();

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isNotEmpty(),
                () -> assertThat(result).hasSize(2)
        );
    }

    @Test
    void save_user_user() {
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(user)
        );
    }

    @Test
    void getById_user_correctData() {
        final long id = 12L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.getById(id);

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(user)
        );
    }

    @Test
    void getById_throwsException_incorrectData() {
        final long id = 12L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getById(id))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Invalid id");
    }

    @Test
    void getByEmail_user_correctData() {
        Mockito.when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.of(user));

        User result = userService.getByEmail(USER_EMAIL);

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result).isEqualTo(user)
        );
    }

    @Test
    void getByEmail_throwsException_incorrectData() {
        Mockito.when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getByEmail(USER_EMAIL))
                .isInstanceOf(UsernameNotFoundException.class).hasMessage("Incorrect combination of email and/or password");
    }


}