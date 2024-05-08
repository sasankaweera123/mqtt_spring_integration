package com.example.mqtt_backend.repository;

import com.example.mqtt_backend.entity.QrTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrTransactionRepository extends JpaRepository<QrTransaction, Long> {
}
