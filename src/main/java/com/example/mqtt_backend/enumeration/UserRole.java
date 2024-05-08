package com.example.mqtt_backend.enumeration;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Admin"),
    OPERATOR("Operator"),
    BANKUSER("Bank User");

    private final String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

}
