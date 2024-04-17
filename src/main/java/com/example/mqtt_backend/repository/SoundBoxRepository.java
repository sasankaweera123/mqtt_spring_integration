package com.example.mqtt_backend.repository;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.enumeration.BankCode;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface SoundBoxRepository extends JpaRepository<SoundBoxDetails, Long> {
    Page<SoundBoxDetails> findAllByBankCode(BankCode bankCode, Pageable pageable);
    Page<SoundBoxDetails> findAllBySoundBoxStatus(SoundBoxStatus soundBoxStatus, Pageable pageable);
    Page<SoundBoxDetails> findAllByBankCodeAndSoundBoxStatus(BankCode bankCode, SoundBoxStatus soundBoxStatus, Pageable pageable);
}
