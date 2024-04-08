package com.example.mqtt_backend.modal.dto;

import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import lombok.Data;

@Data
public class SoundBoxForm {
    private String updateSerialNumber;
    private String updateStatus;

    public SoundBoxForm() {
    }

    public SoundBoxForm(String updateSerialNumber, String updateStatus) {
        this.updateSerialNumber = updateSerialNumber;
        this.updateStatus = updateStatus;
    }
}
