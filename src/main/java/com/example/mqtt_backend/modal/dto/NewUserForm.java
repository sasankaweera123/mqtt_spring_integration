package com.example.mqtt_backend.modal.dto;

import com.example.mqtt_backend.enumeration.UserRole;
import lombok.Data;

@Data
public class NewUserForm {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    private UserRole userRole;

    public NewUserForm() {
    }

    public NewUserForm(String email, String username, String password, String confirmPassword, UserRole userRole) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userRole = userRole;
    }

}
