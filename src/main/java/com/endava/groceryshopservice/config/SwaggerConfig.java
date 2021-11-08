package com.endava.groceryshopservice.config;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Grocery Shop",
                "Grocery Shop REST APIs Documentation",
                "1.0.0",
                "www.hello.com",
                new Contact("Mister XYZ", "www.hello.com", "mrXYZ@gmail.com"),
                "License API",
                "www.hello.com",
                Collections.emptyList()
        );
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.endava.groceryshopservice"))
                .paths(PathSelectors.any())
                .build();
    }
}
