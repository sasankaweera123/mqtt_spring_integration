package com.example.mqtt_backend.controller.api;


import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.modal.Response;
import com.example.mqtt_backend.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_EXTENDED;


@RestController
@RequestMapping(ResourcePath.MQTT)
public class MqttController {

    private final MqttService mqttService;

    @Autowired
    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    /**
     * Send message to MQTT broker
     * @param message message to send
     * @return response entity
     */
    @PostMapping("/send")
    public ResponseEntity<Response> sendMessage(@RequestBody String message) {
        try {
            mqttService.mqttPublish(message);
            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .status(OK)
                            .statusCode(OK.value())
                            .message("Message sent to MQTT broker")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .status(NOT_EXTENDED)
                            .statusCode(NOT_EXTENDED.value())
                            .message("Failed to send message to MQTT broker")
                            .developerMessage(e.getMessage())
                            .build()
            );
        }
    }
}
