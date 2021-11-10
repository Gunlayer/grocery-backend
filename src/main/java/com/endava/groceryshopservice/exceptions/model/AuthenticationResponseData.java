package com.endava.groceryshopservice.exceptions.model;

import com.endava.groceryshopservice.entities.dto.ItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseData {
    private String email;
    private String token;
    private List<ItemResponseDTO> cartItems;
}