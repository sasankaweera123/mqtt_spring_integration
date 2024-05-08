package com.example.mqtt_backend.beans;

import com.example.mqtt_backend.entity.QrTransaction;
import com.example.mqtt_backend.repository.QrTransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class QrTransactionConfig {

    @Bean
    CommandLineRunner qrTransactionCommandLineRunner(QrTransactionRepository qrTransactionRepository){
        return args -> {
            QrTransaction qrTransaction = new QrTransaction("admin", "2005", "QR Success", "200","250", LocalDateTime.now(),"123459867", "200", "mid1","tid1");
            QrTransaction qrTransaction2 = new QrTransaction("admin", "2005", "QR Success", "200","250", LocalDateTime.now(),"123459867", "200", "mid1","tid1");

            qrTransactionRepository.saveAll(List.of(qrTransaction, qrTransaction2));
        };
    }
}
