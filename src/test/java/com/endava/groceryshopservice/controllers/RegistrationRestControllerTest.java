package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.entities.dto.RegistrationResponseDTO;
import com.endava.groceryshopservice.services.RegistrationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.endava.groceryshopservice.utils.RegistrationReqDtoUtils.REGISTRATION_REQUEST;
import static com.endava.groceryshopservice.utils.TestConstants.TOKEN;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegistrationRestController.class)
class RegistrationRestControllerTest extends BaseController {
    @MockBean
    private RegistrationService registrationService;

    @Test
    void register_registrationResponseData_validData() throws Exception {
        RegistrationResponseDTO responseData = RegistrationResponseDTO.builder()
                .email(REGISTRATION_REQUEST.getEmail()).token(TOKEN).build();
        when(registrationService.register(REGISTRATION_REQUEST)).thenReturn(ResponseEntity.ok(responseData));

        mockMvc.perform(
                        post("/registration")
                                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .content(createJsonString(REGISTRATION_REQUEST))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(responseData)));
    }
}