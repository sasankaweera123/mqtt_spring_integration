package com.example.mqtt_backend.beans;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.enumeration.BankCode;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import com.example.mqtt_backend.repository.SoundBoxRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class SoundBoxConfig {

    @Bean
    CommandLineRunner commandLineRunner(SoundBoxRepository soundBoxRepository) {
        return args -> {
            SoundBoxDetails S1 = new SoundBoxDetails("S1", "m1","ma1","mid1","tid1", LocalDate.now(), BankCode.COM_BANK,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S2 = new SoundBoxDetails("S2", "m2","ma2","mid2","tid2", LocalDate.now(), BankCode.BOC,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S3 = new SoundBoxDetails("S3", "m3","ma3","mid3","tid3", LocalDate.now(), BankCode.DFCC,SoundBoxStatus.DISMANTLED);
            SoundBoxDetails S4 = new SoundBoxDetails("S4", "m4","ma4","mid4","tid4", LocalDate.now(), BankCode.HNB,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S5 = new SoundBoxDetails("S5", "m5","ma5","mid5","tid5", LocalDate.now(), BankCode.NDB,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S6 = new SoundBoxDetails("S6", "m6","ma6","mid6","tid6", LocalDate.now(), BankCode.PEOPLES,SoundBoxStatus.TESTING);
            SoundBoxDetails S7 = new SoundBoxDetails("S7", "m7","ma7","mid7","tid7", LocalDate.now(), BankCode.SAMPATH,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S8 = new SoundBoxDetails("S8", "m8","ma8","mid8","tid8", LocalDate.now(), BankCode.SDB,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S9 = new SoundBoxDetails("S9", "m9","ma9","mid9","tid9", LocalDate.now(), BankCode.SEYLAN,SoundBoxStatus.MAINTENANCE);
            SoundBoxDetails S10 = new SoundBoxDetails("S10", "m10","ma10","mid10","tid10", LocalDate.now(), BankCode.COM_BANK,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S11 = new SoundBoxDetails("S11", "m11","ma11","mid11","tid11", LocalDate.now(), BankCode.BOC,SoundBoxStatus.DELIVERED);
            SoundBoxDetails S12 = new SoundBoxDetails("S12", "m12","ma12","mid12","tid12", LocalDate.now(), BankCode.DFCC,SoundBoxStatus.DELIVERED);


            soundBoxRepository.saveAll(List.of(S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11, S12));
        };
    }
}
