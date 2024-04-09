package com.example.mqtt_backend.beans;

import com.example.mqtt_backend.entity.LoginUsers;

import com.example.mqtt_backend.enumeration.UserRole;
import com.example.mqtt_backend.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UsersService usersService) {
        return args -> {
            usersService.addUser(new LoginUsers("admin@gmail.com", "admin", "admin", UserRole.ADMIN));
            usersService.addUser(new LoginUsers("customer@mail.com", "customer", "customer", UserRole.BANKUSER));
            usersService.addUser(new LoginUsers("banker@gmail.com", "banker", "banker", UserRole.BANKUSER));
        };
    }
}
