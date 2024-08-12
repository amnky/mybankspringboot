package com.techlabs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "account_number")
    @NotNull(message = "account number can't be null")
    private int AccountNumber;

    @Column(name = "customer_id")
    @NotNull(message = "customerId can't be null")
    private int customerId;
    @Column(name = "unique_identification_number")
    @NotBlank(message = "unique identification number can't be null")
    private String uniqueIdentificationNumber;


    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUniqueIdentificationNumber() {
        return uniqueIdentificationNumber;
    }

    public void setUniqueIdentificationNumber(String uniqueIdentificationNumber) {
        this.uniqueIdentificationNumber = uniqueIdentificationNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountNumber=" + AccountNumber +
                ", customerId=" + customerId +
                ", uniqueIdentificationNumber=" + uniqueIdentificationNumber +
                '}';
    }
}
