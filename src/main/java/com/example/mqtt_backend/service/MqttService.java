package com.example.mqtt_backend.service;

import com.example.mqtt_backend.gateway.MqttGateway;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    private final MqttGateway mqttGateway;

    @Autowired
    public MqttService(MqttGateway mqttGateway) {
        this.mqttGateway = mqttGateway;
    }

    public void mqttPublish(String response) {
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
        mqttGateway.sendToMqtt(jsonObject.get("message").toString().replace("\"", ""), jsonObject.get("topic").toString().replace("\"", ""));
        System.out.println(jsonObject.get("topic").toString().replace("\"", ""));
    }

    public void mqttPublishViaPortal(String topic, String message) {
        mqttGateway.sendToMqtt(message, topic);
    }
}
