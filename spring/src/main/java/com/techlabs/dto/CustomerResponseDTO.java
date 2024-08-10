package com.techlabs.dto;

public class CustomerResponseDTO {
    private String firstName;
    private String lastName;
    private int accountNumber;
    private int balance;
    private String role;
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
    public CustomerResponseDTO(String firstName, String lastName,String role,int customerId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.customerId=customerId;

    }
    public CustomerResponseDTO(String firstName, String lastName,String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
