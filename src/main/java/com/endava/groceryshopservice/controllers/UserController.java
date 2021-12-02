package com.endava.groceryshopservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.dto.UserInformationDto;
import com.endava.groceryshopservice.services.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.endava.groceryshopservice.entities.User.toUserInformationDto;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Api(value = "User controller is used to perform CRUD operations with users")
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('users:write')")
    @ApiOperation(value = "fetches all the users")
    public ResponseEntity<Page<UserInformationDto>> getAll(Pageable page, @RequestParam(name = "email", defaultValue = "") String email) {
        List<UserInformationDto> users = userService.getAll(page, email)
                .stream()
                .map(User::toUserInformationDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new PageImpl<>(users, page, userService.getCountOfUsersWithEmail(email)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    @ApiOperation(value = "introduces new user")
    public ResponseEntity<UserInformationDto> addUser(@RequestBody UserInformationDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toUserInformationDto(userService.addUser(user)));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('users:write')")
    @ApiOperation(value = "modifies an existing user")
    public ResponseEntity<UserInformationDto> editUser(@RequestBody UserInformationDto user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(toUserInformationDto(userService.editUser(user)));
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('users:write')")
    @ApiOperation(value = "deletes an existing user by email")
    public ResponseEntity<UserInformationDto> deleteUser(@RequestBody String userEmail) {
        return ResponseEntity.status(HttpStatus.OK).body(toUserInformationDto(userService.deleteUser(userEmail)));
    }
}
