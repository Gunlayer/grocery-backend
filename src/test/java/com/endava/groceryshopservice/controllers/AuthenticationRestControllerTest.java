package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.dto.AddressDTO;
import com.endava.groceryshopservice.entities.dto.AuthenticationResponseDTO;
import com.endava.groceryshopservice.services.AddressService;
import com.endava.groceryshopservice.services.ItemService;
import com.endava.groceryshopservice.services.UserService;

import com.endava.groceryshopservice.services.VisitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.endava.groceryshopservice.utils.AddressUtils.ADDRESS_ONE;
import static com.endava.groceryshopservice.utils.RegistrationReqDtoUtils.REGISTRATION_REQUEST;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.VISITOR_ID;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthenticationRestController.class)
class AuthenticationRestControllerTest extends BaseController {

    private final String TOKEN = "12345TREWQ";

    @MockBean
    private UserService userService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private SecurityContextLogoutHandler securityContextLogoutHandler;

    @MockBean
    private AddressService addressService;

    @MockBean
    private VisitorService visitorService;

    @Test
    void whenAuthenticateShouldReturnResponseEntity() throws Exception {
        AuthenticationResponseDTO authenticationResponseDTO = AuthenticationResponseDTO.builder()
                .email(USER_ONE.getEmail())
                .token(TOKEN)
                .address(new AddressDTO(ADDRESS_ONE))
                .cartItems(itemService.findUserCart(USER_ONE.getEmail()))
                .build();
        when(userService.getByEmail(USER_ONE.getEmail())).thenReturn(USER_ONE);
        when(tokenProvider.createToken(USER_ONE.getEmail(), USER_ONE.getRole().name()))
                .thenReturn(TOKEN);
        when(addressService.findByEmail(USER_EMAIL)).thenReturn(ADDRESS_ONE);
        doNothing().when(visitorService).deleteVisitor(VISITOR_ID);

        mockMvc.perform(
                        post("/auth/login")
                                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .content(createJsonString(REGISTRATION_REQUEST))
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(createJsonString(authenticationResponseDTO)));
    }
}