package com.example.mqtt_backend.service;

import com.example.mqtt_backend.entity.MqttRequest;
import com.example.mqtt_backend.enumeration.MqttProcess;
import com.example.mqtt_backend.gateway.MqttGateway;
import com.example.mqtt_backend.repository.MqttRequestRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MqttService {

    private final MqttGateway mqttGateway;
    private final MqttRequestRepository mqttRequestRepository;

    @Autowired
    public MqttService(MqttGateway mqttGateway, MqttRequestRepository mqttRequestRepository) {
        this.mqttGateway = mqttGateway;
        this.mqttRequestRepository = mqttRequestRepository;
    }

    public void mqttPublish(String response) {
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        MqttRequest mqttRequest = new MqttRequest();
        mqttRequest.setTopic(jsonObject.get("topic").toString().replace("\"", ""));
        mqttRequest.setMessage(jsonObject.get("message").toString().replace("\"", ""));
        mqttRequest.setTimeStamp(LocalDateTime.now());


        try {
            mqttGateway.sendToMqtt(jsonObject.get("message").toString().replace("\"", ""), jsonObject.get("topic").toString().replace("\"", ""));
            mqttRequest.setProcess(MqttProcess.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            mqttRequest.setProcess(MqttProcess.FAILED);
            throw e;
        }

        saveMqttRequest(mqttRequest);
        System.out.println(jsonObject.get("topic").toString().replace("\"", ""));
    }

    public void saveMqttRequest(MqttRequest mqttRequest) {
        mqttRequestRepository.save(mqttRequest);
    }

    public void mqttPublishViaPortal(String topic, String message) {
        MqttRequest mqttRequest = new MqttRequest();
        mqttRequest.setTopic(topic);
        mqttRequest.setMessage(message);
        mqttRequest.setTimeStamp(LocalDateTime.now());
        try{
            mqttGateway.sendToMqtt(message, topic);
            mqttRequest.setProcess(MqttProcess.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            mqttRequest.setProcess(MqttProcess.FAILED);
            throw e;
        }
        saveMqttRequest(mqttRequest);

    }
}
