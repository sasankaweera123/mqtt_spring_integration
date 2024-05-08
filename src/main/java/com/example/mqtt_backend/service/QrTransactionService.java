package com.example.mqtt_backend.service;

import com.example.mqtt_backend.entity.QrTransaction;
import com.example.mqtt_backend.repository.QrTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QrTransactionService {

    private final QrTransactionRepository qrTransactionRepository;

    public QrTransactionService(QrTransactionRepository qrTransactionRepository) {
        this.qrTransactionRepository = qrTransactionRepository;
    }

    public List<QrTransaction> getAllTransactions() {
        return qrTransactionRepository.findAll();
    }

    public QrTransaction getTransactionById(Long id) {
        return qrTransactionRepository.findById(id).orElse(null);
    }

    public void addTransaction(QrTransaction qrTransaction) {
        if(qrTransactionRepository.findById(qrTransaction.getId()).isPresent()) {
            throw new IllegalArgumentException("Transaction already exist");
        }
        qrTransactionRepository.save(qrTransaction);
    }

}
