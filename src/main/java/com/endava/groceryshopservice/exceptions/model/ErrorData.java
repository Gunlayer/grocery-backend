package com.endava.groceryshopservice.exceptions.model;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorData {
    private LocalDateTime timeStamp;
    private String message;
    private HttpStatus status;
}