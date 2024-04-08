package com.example.mqtt_backend.modal.dto;

import lombok.Data;

@Data
public class UserLoginForm {
    private String email;
    private String password;

    public UserLoginForm() {
    }

    public UserLoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
