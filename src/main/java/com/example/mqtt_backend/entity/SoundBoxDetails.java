package com.example.mqtt_backend.entity;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.enumeration.BankCode;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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

    private String mName;
    private String mAddress;
    private String mid;
    private String tid;
    private LocalDate dateAdded;

    @Enumerated(EnumType.STRING)
    private BankCode bankCode;

    private String param1;
    private String param2;

    @Enumerated(EnumType.STRING)
    private SoundBoxStatus soundBoxStatus;


    public SoundBoxDetails() {
    }


    public SoundBoxDetails(String serialNumber, String mName, String mAddress, String mid, String tid, LocalDate dateAdded, BankCode bankCode, SoundBoxStatus soundBoxStatus) {
        this.serialNumber = serialNumber;
        this.topic = ResourcePath.SOUNDBOX_SERIAL_HEADER + serialNumber;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mid = mid;
        this.tid = tid;
        this.dateAdded = dateAdded;
        this.bankCode = bankCode;
        this.param1 = null;
        this.param2 = null;
        this.soundBoxStatus = soundBoxStatus;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        this.topic = ResourcePath.SOUNDBOX_SERIAL_HEADER + serialNumber;
    }

    public void setTopic(String topic) {
        this.topic = ResourcePath.SOUNDBOX_SERIAL_HEADER + this.serialNumber;
    }


}
