package com.example.mqtt_backend.enumeration;

import lombok.Getter;

@Getter
public enum SoundBoxStatus {
    PRODUCTION("Production"),
    TESTING("Testing"),
    DELIVERED("Delivered"),
    INSTALLED("Installed"),
    MAINTENANCE("Maintenance"),
    DISMANTLED("Dismantled");

    private final String soundBoxStatus;

    SoundBoxStatus(String soundBoxStatus) {
        this.soundBoxStatus = soundBoxStatus;
    }

}
