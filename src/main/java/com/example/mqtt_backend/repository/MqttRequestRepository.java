package com.example.mqtt_backend.repository;

import com.example.mqtt_backend.entity.MqttRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MqttRequestRepository extends JpaRepository<MqttRequest, Long> {
    @Query(value = "SELECT MAX(e.id) FROM MqttRequest e")
    Long findMaxId();


    Optional <MqttRequest> findByTopicAndReferenceId(String topic, Long referenceId);
}
