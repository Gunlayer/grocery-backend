package com.endava.groceryshopservice.config.profiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DbConnectionConfig {

    @Profile("local")
    @Bean
    public void localDbConnection() {
    }

    @Profile("remote")
    @Bean
    public void remoteDbConnection() {
    }
}