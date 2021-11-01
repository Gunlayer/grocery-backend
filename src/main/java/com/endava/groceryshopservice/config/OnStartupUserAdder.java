package com.endava.groceryshopservice.config;

import com.endava.groceryshopservice.entities.User;
import com.endava.groceryshopservice.entities.user_permission.Role;
import com.endava.groceryshopservice.entities.user_permission.Status;
import com.endava.groceryshopservice.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OnStartupUserAdder {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User admin = User.builder()
                    .email("admin")
                    .password("$2a$12$WQmh3G/A6nsrnO2pTFd71eAlhWlI9uKNsSLkY5mTgONXxdd4GLbf.")
                    .role(Role.ADMIN)
                    .status(Status.ACTIVE)
                    .build();
            User user = User.builder()
                    .email("user")
                    .password("$2a$12$234bgDgtLpoQDUnGAv/OfuPmi35jqc4.AhhI4VD7wlb.pGUlz6CcC")
                    .role(Role.USER)
                    .status(Status.ACTIVE)
                    .build();

            userRepository.save(admin);
            userRepository.save(user);
        };
    }

}
