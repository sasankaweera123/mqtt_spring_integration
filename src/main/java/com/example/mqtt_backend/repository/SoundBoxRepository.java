package com.example.mqtt_backend.repository;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SoundBoxRepository extends JpaRepository<SoundBoxDetails, Long> {
}
