package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.UserInformationDto;
import com.endava.groceryshopservice.exceptions.AlreadyExistingUserException;
import com.endava.groceryshopservice.exceptions.InvalidEmailException;
import com.endava.groceryshopservice.services.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.endava.groceryshopservice.entities.User.toUserInformationDto;
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_TOKEN;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.ADMIN_ONE;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest extends BaseController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllShouldReturnAllUsers() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);

        when(userService.getAll(PageRequest.of(0, 2), ""))
                .thenReturn(new PageImpl<>(List.of(USER_ONE, ADMIN_ONE)));
        List<UserInformationDto> users = Stream.of(USER_ONE, ADMIN_ONE).map(User::toUserInformationDto).collect(Collectors.toList());

        mockMvc.perform(get("/admin/users?pageNumber=0&pageSize=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(new PageImpl<>(users, PageRequest.of(0, 2), 2))));
    }

    @Test
    void whenAddUserWithCorrectArgShouldReturnNewUser() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);

        when(userService.addUser(any(UserInformationDto.class))).thenReturn(USER_ONE);

        mockMvc.perform(post("/admin/users")
                        .content(createJsonString(toUserInformationDto(USER_ONE)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(createJsonString(toUserInformationDto(USER_ONE))));
    }

    @Test
    void whenAddUserWithIncorrectArgShouldThrowException() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);

        when(userService.addUser(any(UserInformationDto.class))).thenThrow(new AlreadyExistingUserException("User with the same email already exists"));

        mockMvc.perform(post("/admin/users")
                        .content(createJsonString(toUserInformationDto(USER_ONE)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("User with the same email already exists"));
    }

    @Test
    void whenEditUserWithCorrectArgShouldReturnEditedUser() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);

        when(userService.editUser(any(UserInformationDto.class))).thenReturn(USER_ONE);

        mockMvc.perform(put("/admin/users")
                        .content(createJsonString(toUserInformationDto(USER_ONE)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json(createJsonString(toUserInformationDto(USER_ONE))));
    }

    @Test
    void whenEditUserWithIncorrectArgShouldThrowException() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);

        when(userService.editUser(any(UserInformationDto.class))).thenThrow(new InvalidEmailException("Not existing email"));

        mockMvc.perform(put("/admin/users")
                        .content(createJsonString(toUserInformationDto(USER_ONE)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Not existing email"));
    }

    @Test
    void whenDeleteUserWithCorrectArgShouldReturnDeletedUser() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);

        when(userService.deleteUser(USER_EMAIL)).thenReturn(USER_ONE);

        mockMvc.perform(delete("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_EMAIL))
                .andExpect(status().isOk())
                .andExpect(content().json(createJsonString(toUserInformationDto(USER_ONE))));
    }

    @Test
    void whenDeleteUserWithIncorrectArgShouldThrowException() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);

        when(userService.deleteUser(USER_EMAIL)).thenThrow(new InvalidEmailException("Not existing email"));

        mockMvc.perform(delete("/admin/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(USER_EMAIL))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Not existing email"));
    }
}