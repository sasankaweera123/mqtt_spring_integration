package com.example.mqtt_backend.entity;

import com.example.mqtt_backend.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class LoginUsers {
    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    private Long id;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public LoginUsers() {
    }

public LoginUsers(String email, String username, String password, UserRole userRole) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
}
