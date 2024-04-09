package com.example.mqtt_backend.enumeration;

public enum UserRole {
    ADMIN("Admin"),
    BANKUSER("Bank User");

    private final String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

}
