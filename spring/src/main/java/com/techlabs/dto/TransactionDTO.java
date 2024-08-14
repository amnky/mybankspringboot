package com.techlabs.dto;

import com.techlabs.utils.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionDTO {

    @NotNull
    private int receiverAccountNumber;
    @NotNull
    private int senderCustomerId;
    @NotNull
    private int transactionAmount;
    private TransactionType transactionType;

    public TransactionDTO() {
    }

    public TransactionDTO(int receiverAccountNumber, int senderCustomerId, int transactionAmount,String transactionType) {
        this.receiverAccountNumber = receiverAccountNumber;
        this.senderCustomerId = senderCustomerId;
        this.transactionAmount = transactionAmount;
        this.transactionType=TransactionType.valueOf(transactionType);
    }

    public int getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(int receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public int getSenderCustomerId() {
        return senderCustomerId;
    }

    public void setSenderCustomerId(int senderCustomerId) {
        this.senderCustomerId = senderCustomerId;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
