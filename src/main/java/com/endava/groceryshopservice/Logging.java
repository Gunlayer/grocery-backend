package com.endava.groceryshopservice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class Logging {
    @Pointcut("within(com.endava.groceryshopservice.controllers..*)")
    public void callRestControllers() {
    }

    @Before("callRestControllers()")
    public void beforeExecuteRequest(JoinPoint point) {
        String params = Arrays.stream(point.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("It was called {} with next params: {}", point, params);
    }

    @AfterReturning(value = "callRestControllers()", returning = "value")
    public void afterExecuteRequest(JoinPoint point, Object value) {
        log.info("Method {} return next value: {}", point, value);
    }
}
