package com.example.mqtt_backend.beans;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import com.example.mqtt_backend.repository.SoundBoxRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SoundBoxConfig {

    @Bean
    CommandLineRunner commandLineRunner(SoundBoxRepository soundBoxRepository) {
        return args -> {
            SoundBoxDetails S1 = new SoundBoxDetails("S1", "topic1", SoundBoxStatus.DELIVERED);
            SoundBoxDetails S2 = new SoundBoxDetails("S2", "topic2", SoundBoxStatus.INSTALLED);
            SoundBoxDetails S3 = new SoundBoxDetails("S3", "topic3", SoundBoxStatus.MAINTENANCE);
            SoundBoxDetails S4 = new SoundBoxDetails("S4", "topic4", SoundBoxStatus.PRODUCTION);
            SoundBoxDetails S5 = new SoundBoxDetails("S5", "topic5", SoundBoxStatus.TESTING);

            soundBoxRepository.saveAll(List.of(S1, S2, S3, S4, S5));
        };
    }
}
