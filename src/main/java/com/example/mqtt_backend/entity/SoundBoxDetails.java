package com.example.mqtt_backend.entity;

import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sound_box_details")
public class SoundBoxDetails {
    @Id
    @SequenceGenerator(name = "sound_box_details_sequence", sequenceName = "sound_box_details_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sound_box_details_sequence")
    private long soundBoxId;
    private String serialNumber;
    private String topic;
    private SoundBoxStatus soundBoxStatus;


    public SoundBoxDetails() {
    }
    public SoundBoxDetails(String serialNumber, String topic, SoundBoxStatus soundBoxStatus) {
        this.serialNumber = serialNumber;
        this.topic = topic;
        this.soundBoxStatus = soundBoxStatus;
    }



}
