package com.example.mqtt_backend.enumeration;

import lombok.Getter;

@Getter
public enum Process {
    SUCCESS("Success"),
    FAILED("Failed"),
    PENDING("Pending");

    private final String process;

    Process(String process) {
        this.process = process;
    }

}
