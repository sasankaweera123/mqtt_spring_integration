package com.example.mqtt_backend.repository;

import com.example.mqtt_backend.entity.MqttRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MqttRequestRepository extends JpaRepository<MqttRequest, Long> {
}
