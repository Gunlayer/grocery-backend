package com.endava.groceryshopservice.controllers;

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

import static com.endava.groceryshopservice.utils.AddressUtils.ADDRESS_ONE;
import static com.endava.groceryshopservice.utils.CheckoutRequest.CHECKOUT_REQUEST;
import static com.endava.groceryshopservice.utils.TestConstants.TOKEN;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
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
        when(addressService.save(ADDRESS_ONE)).thenReturn(ADDRESS_ONE);
        when(tokenProvider.getUsername(TOKEN)).thenReturn(USER_EMAIL);
        when(userService.getByEmail(USER_EMAIL)).thenReturn(USER_ONE);

        mvc.perform(post("/checkout")
                        .header("authorization", TOKEN)
                        .content(createJsonString(CHECKOUT_REQUEST))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(addressService).save(ADDRESS_ONE);
    }
}