package com.techlabs.dto;

import com.techlabs.utils.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
