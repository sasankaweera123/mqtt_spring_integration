package com.example.mqtt_backend.beans;

import com.example.mqtt_backend.entity.LoginUsers;

import com.example.mqtt_backend.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UsersService usersService) {
        return args -> {
            usersService.addUser(new LoginUsers("admin@gmail.com", "admin", "admin"));
        };
    }
}
