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
            SoundBoxDetails S6 = new SoundBoxDetails("S6", "topic6", SoundBoxStatus.DELIVERED);
            SoundBoxDetails S7 = new SoundBoxDetails("S7", "topic7", SoundBoxStatus.INSTALLED);
            SoundBoxDetails S8 = new SoundBoxDetails("S8", "topic8", SoundBoxStatus.MAINTENANCE);
            SoundBoxDetails S9 = new SoundBoxDetails("S9", "topic9", SoundBoxStatus.PRODUCTION);
            SoundBoxDetails S10 = new SoundBoxDetails("S10", "topic10", SoundBoxStatus.TESTING);
            SoundBoxDetails S11 = new SoundBoxDetails("S11", "topic11", SoundBoxStatus.DELIVERED);

            soundBoxRepository.saveAll(List.of(S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11));
        };
    }
}
