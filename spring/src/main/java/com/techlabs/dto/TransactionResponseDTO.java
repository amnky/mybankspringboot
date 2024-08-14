package com.techlabs.dto;

import com.techlabs.utils.TransactionType;

import java.time.LocalDateTime;
import java.util.Date;

public class TransactionResponseDTO {
    private int SenderAccountNo;
    private int ReceiverAccountNo;
    private int amount;
    private LocalDateTime transactionDate;
    public TransactionResponseDTO(){}

    public TransactionResponseDTO(int senderAccountNo, int receiverAccountNo, int amount, LocalDateTime transactionDate) {
        SenderAccountNo = senderAccountNo;
        ReceiverAccountNo = receiverAccountNo;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public int getSenderAccountNo() {
        return SenderAccountNo;
    }

    public void setSenderAccountNo(int senderAccountNo) {
        SenderAccountNo = senderAccountNo;
    }

    public int getReceiverAccountNo() {
        return ReceiverAccountNo;
    }

    public void setReceiverAccountNo(int receiverAccountNo) {
        ReceiverAccountNo = receiverAccountNo;
    }

//    public TransactionType getTransactionType() {
//        return transactionType;
//    }
//
//    public void setTransactionType(TransactionType transactionType) {
//        this.transactionType = transactionType;
//    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
