package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.services.ItemService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.endava.groceryshopservice.utils.ItemUtils.ITEM_RESPONSE_DTO;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartController.class)
class CartControllerTest extends BaseController {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @Test
    @WithMockUser
    void shouldGetUserCard() throws Exception {
        when(itemService.findUserCart(USER_EMAIL)).thenReturn(ITEM_RESPONSE_DTO);
        mockMvc.perform(get("/cart/test@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createExpectedBody(ITEM_RESPONSE_DTO)));

        verify(itemService).findUserCart(USER_EMAIL);
    }
}