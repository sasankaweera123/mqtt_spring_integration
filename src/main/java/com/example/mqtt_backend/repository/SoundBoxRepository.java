package com.example.mqtt_backend.repository;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundBoxRepository extends JpaRepository<SoundBoxDetails, Long> {
}
