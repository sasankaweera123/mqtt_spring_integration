package com.example.mqtt_backend.controller;

import com.example.mqtt_backend.gateway.MqttGateway;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {

    @Autowired
    MqttGateway mqttGateway;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {

        try {
            JsonObject jsonObject = new Gson().fromJson(message, JsonObject.class);
            mqttGateway.sendToMqtt(jsonObject.get("message").toString(), jsonObject.get("topic").toString());
            return ResponseEntity.ok("Message sent to MQTT broker");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error sending message to MQTT broker");
        }
    }
}
