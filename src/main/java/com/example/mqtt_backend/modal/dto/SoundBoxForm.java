package com.example.mqtt_backend.modal.dto;

import com.example.mqtt_backend.enumeration.BankCode;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import lombok.Data;

@Data
public class SoundBoxForm {
    private String serialNumber;
    private String mName;
    private String mAddress;
    private String mid;
    private String tid;
    private String dateAdded;
    private BankCode bankCode;
    private SoundBoxStatus soundBoxStatus;

    public SoundBoxForm() {}

}
