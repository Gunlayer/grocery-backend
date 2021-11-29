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
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_TOKEN;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.UserUtils.ADMIN_ONE;
import static com.endava.groceryshopservice.utils.UserUtils.USER_ONE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

//    @Test
//    @WithMockUser
//    void shouldGetDashboard(){
//        prepareAuthorizedRequestForUser(ADMIN_ONE, ADMIN_TOKEN);
//
//        when(dashboardService.getWeeklyDashboard(USER_EMAIL)).thenReturn(USER_ONE);
//
//        mockMvc.perform(get("/admin/users/" + USER_EMAIL))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//    }
}
