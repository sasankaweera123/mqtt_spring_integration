package com.example.mqtt_backend.modal.dto;

import lombok.Data;

@Data
public class MqttReceive {
    private String topic;
    private long referenceId;

    public MqttReceive() {
    }

    public MqttReceive(String topic, long referenceId) {
        this.topic = topic;
        this.referenceId = referenceId;
    }
}
