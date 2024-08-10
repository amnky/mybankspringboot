package com.techlabs.dto;

public class TransferResponseDTO {
    private int transactionId;
    private int balance;
    private int sendMoney;

    public TransferResponseDTO(){}
    public TransferResponseDTO(int transactionId,int balance, int sendMoney) {
        this.transactionId=transactionId;
        this.balance = balance;
        this.sendMoney = sendMoney;
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(int sendMoney) {
        this.sendMoney = sendMoney;
    }
}
