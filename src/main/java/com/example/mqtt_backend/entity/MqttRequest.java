package com.example.mqtt_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mqtt_request")
public class MqttRequest {

    @Id
    @SequenceGenerator(name = "mqtt_request_sequence", sequenceName = "mqtt_request_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mqtt_request_sequence")
    private long id;
    private String topic;
    private String message;
    private LocalDateTime timeStamp;

    public MqttRequest() {
    }

    public MqttRequest(String topic, String message, LocalDateTime timeStamp) {
        this.topic = topic;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
