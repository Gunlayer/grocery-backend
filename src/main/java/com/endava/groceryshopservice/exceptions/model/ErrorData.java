package com.endava.groceryshopservice.exceptions.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorData {
    private LocalDateTime timeStamp;
    private String errorMessage;
    private HttpStatus status;
}
