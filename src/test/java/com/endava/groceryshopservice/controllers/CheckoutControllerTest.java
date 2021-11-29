package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.user_permission.Status;
import com.endava.groceryshopservice.services.AddressService;
import com.endava.groceryshopservice.services.ProductService;
import com.endava.groceryshopservice.services.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.endava.groceryshopservice.entities.user_permission.Role.USER;
import static com.endava.groceryshopservice.utils.CheckoutRequest.CHECKOUT_REQUEST;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.USER_PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.USER_TOKEN;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CheckoutController.class)
class CheckoutControllerTest extends BaseController {

    @Autowired
    MockMvc mvc;

    @MockBean
    AddressService addressService;

    @MockBean
    UserService userService;

    @MockBean
    ProductService productService;

    @Test
    void shouldSaveAddressAtCheckout() throws Exception {
        User user = User.builder()
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .role(USER)
                .status(Status.ACTIVE)
                .build();
        when(tokenProvider.getUsername(USER_TOKEN)).thenReturn(USER_EMAIL);
        when(userService.getByEmail(USER_EMAIL)).thenReturn(user);

        mvc.perform(post("/checkout")
                        .header("authorization", USER_TOKEN)
                        .content(createJsonString(CHECKOUT_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).save(user);
    }
}