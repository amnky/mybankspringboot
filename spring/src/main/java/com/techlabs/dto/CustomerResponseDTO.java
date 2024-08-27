package com.techlabs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private String firstName;
    private String lastName;
    private int accountNumber;
    private int balance;
    private int customerId;
    private String nomineeName;
    private String address;
    private String email;
    private String identificationNumber;

    public CustomerResponseDTO(String firstName, String lastName, int accountNumber, int balance,
                               int customerId, String nomineeName, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
        this.nomineeName = nomineeName;
        this.address = address;
        this.email = email;
    }

    public CustomerResponseDTO(String firstName, String lastName, int accountNumber, int balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public CustomerResponseDTO(String firstName, String lastName, int accountNumber, int balance,int customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId=customerId;
    }
    public CustomerResponseDTO(String firstName, String lastName,int customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerId=customerId;

    }
    public CustomerResponseDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }


}
