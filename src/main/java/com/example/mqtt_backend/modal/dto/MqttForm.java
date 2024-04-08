package com.example.mqtt_backend.modal.dto;

import lombok.Data;

@Data
public class MqttForm {
    private String topic;
    private String message;

    public MqttForm() {
    }

    public MqttForm(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }

}
