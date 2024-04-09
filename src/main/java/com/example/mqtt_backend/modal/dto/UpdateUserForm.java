package com.example.mqtt_backend.modal.dto;

import com.example.mqtt_backend.enumeration.UserRole;
import lombok.Data;

@Data
public class UpdateUserForm {
    private String updateEmail;
    private String updateUsername;
    private String updatePassword;
    private UserRole updateUserRole;

    public UpdateUserForm() {
    }

    public UpdateUserForm(String updateEmail, String updateUsername, String updatePassword, UserRole updateUserRole) {
        this.updateEmail = updateEmail;
        this.updateUsername = updateUsername;
        this.updatePassword = updatePassword;
        this.updateUserRole = updateUserRole;
    }


}
