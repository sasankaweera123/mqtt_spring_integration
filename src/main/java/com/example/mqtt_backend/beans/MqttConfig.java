package com.example.mqtt_backend.beans;

import com.example.mqtt_backend.entity.MqttRequest;
import com.example.mqtt_backend.enumeration.MqttProcess;
import com.example.mqtt_backend.repository.MqttRequestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MqttConfig {

    @Bean
    CommandLineRunner mqttCommandLineRunner(MqttRequestRepository mqttRequestRepository){
        return args -> {
            MqttRequest mqttRequest = new MqttRequest("topic", "message", LocalDateTime.now(), MqttProcess.SUCCESS);
            mqttRequestRepository.save(mqttRequest);
        };
    }
}
