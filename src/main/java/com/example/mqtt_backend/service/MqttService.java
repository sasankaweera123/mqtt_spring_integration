package com.example.mqtt_backend.service;

import com.example.mqtt_backend.controller.UserController;
import com.example.mqtt_backend.entity.MqttRequest;
import com.example.mqtt_backend.enumeration.MqttProcess;
import com.example.mqtt_backend.gateway.MqttGateway;
import com.example.mqtt_backend.repository.MqttRequestRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MqttService {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final MqttGateway mqttGateway;
    private final MqttRequestRepository mqttRequestRepository;

    @Autowired
    public MqttService(MqttGateway mqttGateway, MqttRequestRepository mqttRequestRepository) {
        this.mqttGateway = mqttGateway;
        this.mqttRequestRepository = mqttRequestRepository;
    }

    /**
     * Publish message to MQTT broker
     * @param response The message to publish
     */
    public void mqttPublish(String response) {
        JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);

        long referenceId = mqttRequestRepository.findMaxId();

        String message = jsonObject.get("message").toString().replace("\"", "");
        String messageSend = message + " | " + referenceId;

        MqttRequest mqttRequest = new MqttRequest();
        mqttRequest.setTopic(jsonObject.get("topic").toString().replace("\"", ""));
        mqttRequest.setMessage(message);
        mqttRequest.setReferenceId(referenceId);
        mqttRequest.setTimeStamp(LocalDateTime.now());

        logger.info("Publishing message to topic: {}", jsonObject.get("topic").toString().replace("\"", ""));

        try {
            logger.info("Message: {}", jsonObject.get("message").toString().replace("\"", ""));
            mqttGateway.sendToMqtt(messageSend, jsonObject.get("topic").toString().replace("\"", ""));
            mqttRequest.setProcess(MqttProcess.PENDING);
        } catch (Exception e) {
            logger.error("Failed to publish message to topic: {}", jsonObject.get("topic").toString().replace("\"", ""));
            mqttRequest.setProcess(MqttProcess.FAILED);
            throw e;
        }

        saveMqttRequest(mqttRequest);
        logger.info("Message published to topic: {}", jsonObject.get("topic").toString().replace("\"", ""));
        System.out.println(jsonObject.get("topic").toString().replace("\"", ""));
    }

    /**
     * Save MQTT request to database
     * @param mqttRequest The MQTT request to save
     */

    public void saveMqttRequest(MqttRequest mqttRequest) {
        mqttRequestRepository.save(mqttRequest);
        logger.info("Mqtt request saved to database");
    }

    /**
     * Publish message to MQTT broker via portal
     * @param topic The topic to publish message
     * @param message The message to publish
     */

    public void mqttPublishViaPortal(String topic, String message) {

        long referenceId = mqttRequestRepository.findMaxId();
        logger.info("Publish Reference ID: {}", referenceId);
        String messageSend = message + " | " +referenceId;
        System.out.println(messageSend);
        MqttRequest mqttRequest = new MqttRequest();
        mqttRequest.setTopic(topic);
        mqttRequest.setMessage(message);
        mqttRequest.setReferenceId(referenceId);
        mqttRequest.setTimeStamp(LocalDateTime.now());
        try {
            mqttGateway.sendToMqtt(messageSend, topic);
            logger.info("Message published to topic (Testing) : {}", topic);
            mqttRequest.setProcess(MqttProcess.PENDING);
        } catch (Exception e) {
            logger.error("Failed to publish message to topic (Testing) : {}", topic);
            mqttRequest.setProcess(MqttProcess.FAILED);
            throw e;
        }
        saveMqttRequest(mqttRequest);
        logger.info("Message Saved to Database");
    }

    /**
     * Update MQTT message status in database when message received
     * @param message The message received from MQTT broker
     */

    public void mqttMessageReceived(String message) {
        String topic = getExtractTopic(message);
        long referenceId = getReferenceId(message);
        try{
            MqttRequest mqttRequest = mqttRequestRepository.findByTopicAndReferenceId(topic, referenceId).orElseThrow(() -> new Exception("Mqtt request not found"));
            mqttRequest.setProcess(MqttProcess.SUCCESS);
            mqttRequestRepository.save(mqttRequest);
            logger.info("Mqtt message received and status updated in database");
        } catch (Exception e){
            logger.error(e.getMessage());
            logger.error("Failed to update mqtt message status in database");
        }
    }

    /**
     * Extract reference ID from message
     * @param message The message received from MQTT broker
     * @return Reference ID
     */

    public long getReferenceId(String message){
        Pattern pattern = Pattern.compile("\\|\\s*(\\d+)\\s*$");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            logger.info("Received Reference ID: {}", matcher.group(1));
            return Long.parseLong(matcher.group(1));
        }
        logger.error("Failed to get reference ID");
        return 0;
    }

    /**
     * Extract topic from message
     * @param message The message received from MQTT broker
     * @return Topic
     */

    public String getExtractTopic(String message){
        Pattern pattern = Pattern.compile("^([^|]+)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            logger.info("Topic: {}", matcher.group(1));
            return matcher.group(1).trim();
        }
        logger.error("Failed to get topic");
        return "";
    }
}
