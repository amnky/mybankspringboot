package com.techlabs.dto;

public class CustomerResponseDTO {
    private String firstName;
    private String lastName;
    private int accountNumber;
    private int balance;
    private int customerId;
    public CustomerResponseDTO(){}

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


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
