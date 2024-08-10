package com.techlabs.entity;


import com.techlabs.utils.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="customer_id")
    @NotNull
    private int customerId;

    @Column(name="account_number")
    @NotNull
    private int accountNumber;

    @Column(name="first_name")
    @NotNull
    private String firstName;

    @Column(name="last_name")
    @NotNull
    private String lastName;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name="account_type")
    @NotNull
    private AccountType accountType;

    @Column(name="customer_address")
    @NotNull
    private String  customerAddress;

    @Column(name="nominee_name")
    @NotNull
    private String nomineeName;

    @Column(name="balance")
    @NotBlank
    private int balance;

    @Column(name="account_open_date")
    @NotBlank
    private LocalDateTime accountOpenDate;

    @Column(name="identification_number")
    @NotNull
    private int identificationNumber;
    public Customer(){}

    public Customer(int customerId, int accountNumber, String firstName, String lastName, String email, AccountType accountType, String customerAddress, String nomineeName, int balance, LocalDateTime accountOpenDate, int identificationNumber) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountType = accountType;
        this.customerAddress = customerAddress;
        this.nomineeName = nomineeName;
        this.balance = balance;
        this.accountOpenDate = accountOpenDate;
        this.identificationNumber = identificationNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getAccountOpenDate() {
        return accountOpenDate;
    }

    public void setAccountOpenDate(LocalDateTime accountOpenDate) {
        this.accountOpenDate = accountOpenDate;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", accountNumber=" + accountNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accountType=" + accountType +
                ", customerAddress='" + customerAddress + '\'' +
                ", nomineeName='" + nomineeName + '\'' +
                ", balance=" + balance +
                ", accountOpenDate=" + accountOpenDate +
                ", identificationNumber=" + identificationNumber +
                '}';
    }
}
