package com.techlabs.entity;

import com.techlabs.utils.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="transaction_id")
    private int transactionId;

    @NotNull
    @Column(name="sender_account_no")
    private int senderAccountNo;

    @NotNull
    @Column(name="receiver_account_no")
    private int receiverAccountNo;

    @NotNull
    @Column(name="customer_id")
    private int customerId;

    @Column(name="transaction_time")
    private LocalDateTime transactionTime;

    @NotNull
    @Column(name="transaction_amount")
    private int transactionAmount;

    @NotNull
    @Column(name="status")
    private Boolean status;

    @Column(name="transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    public Transaction(int senderAccountNo, int receiverAccountNo, int customerId, LocalDateTime transactionTime,
                       int transactionAmount, Boolean status, TransactionType transactionType) {
        this.senderAccountNo = senderAccountNo;
        this.receiverAccountNo = receiverAccountNo;
        this.customerId = customerId;
        this.transactionTime = transactionTime;
        this.transactionAmount = transactionAmount;
        this.status = status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getSenderAccountNo() {
        return senderAccountNo;
    }

    public void setSenderAccountNo(int senderAccountNo) {
        this.senderAccountNo = senderAccountNo;
    }

    public int getReceiverAccountNo() {
        return receiverAccountNo;
    }

    public void setReceiverAccountNo(int receiverAccountNo) {
        this.receiverAccountNo = receiverAccountNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(int transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
