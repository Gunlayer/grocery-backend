package com.endava.groceryshopservice.controllers;

import com.endava.groceryshopservice.services.DashboardService;
import com.endava.groceryshopservice.services.VisitorService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.endava.groceryshopservice.utils.DashboardUtils.DASHBOARD;
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_TOKEN;
import static com.endava.groceryshopservice.utils.UserUtils.ADMIN_ONE;
import static com.endava.groceryshopservice.utils.VisitorUtils.VISITOR_REQUEST_DTO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DashboardController.class)
public class DashboardControllerTest extends BaseController{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VisitorService visitorService;

    @MockBean
    DashboardService dashboardService;

    @Test
    @WithMockUser
    void shouldGetDashboard() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);
        when(dashboardService.getWeeklyDashboard()).thenReturn(DASHBOARD);

        mockMvc.perform(get("/dashboard").header("authorization", ADMIN_TOKEN))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(createJsonString(DASHBOARD)));
        verify(dashboardService).getWeeklyDashboard();
    }

    @Test
    @WithMockUser
    void shouldAddNewVisitor() throws Exception {
        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);
        doNothing().when(visitorService).addVisitor(VISITOR_REQUEST_DTO);

        mockMvc.perform(post("/dashboard/visitor").header("authorization", ADMIN_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createJsonString(VISITOR_REQUEST_DTO)))
                .andDo(print())
                .andExpect(status().isCreated());
        verify(visitorService).addVisitor(VISITOR_REQUEST_DTO);
    }
}
