package com.example.mqtt_backend.entity;

import com.example.mqtt_backend.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class QrTransaction {
    @Id
    @SequenceGenerator(name = "qr_transaction_sequence", sequenceName = "qr_transaction_sequence", allocationSize = 1)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "qr_transaction_sequence")
    private long id;

    private String userName;
    private String responseCode;
    private String description;
    private String referenceNumber;
    private String transactionAmount;
    private LocalDateTime transactionDateTime;
    private String customerReferenceNumber;
    private String approveCode;
    private String merchantId;
    private String terminalId;

    public QrTransaction() {
    }

    public QrTransaction(String userName, String responseCode, String description, String referenceNumber, String transactionAmount, LocalDateTime transactionDateTime, String customerReferenceNumber, String approveCode, String merchantId, String terminalId) {
        this.userName = userName;
        this.responseCode = responseCode;
        this.description = description;
        this.referenceNumber = referenceNumber;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = transactionDateTime;
        this.customerReferenceNumber = customerReferenceNumber;
        this.approveCode = approveCode;
        this.merchantId = merchantId;
        this.terminalId = terminalId;
    }


}
