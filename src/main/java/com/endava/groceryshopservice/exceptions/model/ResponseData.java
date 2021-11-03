package com.endava.groceryshopservice.exceptions.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseData {
    private String email;
    private String token;
}