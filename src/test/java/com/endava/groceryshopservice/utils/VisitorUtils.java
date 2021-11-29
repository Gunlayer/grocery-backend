package com.endava.groceryshopservice.utils;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.Visitor;
import com.endava.groceryshopservice.entities.dto.VisitorRequestDTO;
import com.endava.groceryshopservice.entities.user_permission.Status;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;
import static com.endava.groceryshopservice.entities.user_permission.Role.ADMIN;
import static com.endava.groceryshopservice.entities.user_permission.Role.USER;
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.ADMIN_PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.USER_EMAIL;
import static com.endava.groceryshopservice.utils.TestConstants.USER_PASSWORD;
import static com.endava.groceryshopservice.utils.TestConstants.VISITOR_ID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VisitorUtils {
    public static final Visitor VISITOR_ONE = Visitor.builder()
            .visitorId(VISITOR_ID)
            .addingDate(LocalDate.now())
            .build();

    public static final VisitorRequestDTO VISITOR_REQUEST_DTO = VisitorRequestDTO.builder()
            .visitorId(VISITOR_ID)
            .build();

    public static final List<Visitor> VISITOR_LIST = List.of(VISITOR_ONE);

}
