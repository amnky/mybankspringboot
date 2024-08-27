package com.techlabs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDTO {
    private int transactionId;
    private int balance;
    private int sendMoney;

    private Boolean status;
    public TransferResponseDTO(int transactionId,int balance, int sendMoney) {
        this.transactionId=transactionId;
        this.balance = balance;
        this.sendMoney = sendMoney;
    }
}
