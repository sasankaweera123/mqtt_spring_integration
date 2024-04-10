package com.example.mqtt_backend.enumeration;

import lombok.Getter;

@Getter
public enum MqttProcess {
    SUCCESS("Success"),
    FAILED("Failed"),
    PENDING("Pending");

    private final String process;

    MqttProcess(String process) {
        this.process = process;
    }

}
