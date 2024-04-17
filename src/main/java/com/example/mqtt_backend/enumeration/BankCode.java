package com.example.mqtt_backend.enumeration;

import lombok.Getter;

@Getter
public enum BankCode {
    ALL("ALL"),
    BOC("BOC"),
    HNB("HNB"),
    NDB("NDB"),
    PEOPLES("PEOPLES"),
    SAMPATH("SAMP"),
    SDB("SDB"),
    SEYLAN("SEYLAN"),
    COM_BANK("COM_BANK"),
    DFCC("DFCC");

    private final String bankCode;

    BankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
