package com.techlabs.entity;


import com.techlabs.utils.AccountType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name="customer_address")
    @NotNull
    private String  customerAddress;

    @Column(name="nominee_name")
    @NotNull
    private String nomineeName;

    @Column(name="balance")
    @NotNull
    private int balance;
    @Column(name="is_active")
    private boolean isActive;

    @Column(name="account_open_date")
    @DateTimeFormat
    private LocalDateTime accountOpenDate;

    @Column(name="identification_number")
    @NotNull
    private int identificationNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Customer(){}

    public Customer(int customerId, int accountNumber, String firstName, String lastName, String email,
                    String customerAddress, String nomineeName, int balance, LocalDateTime accountOpenDate, int identificationNumber) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", accountNumber=" + accountNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", nomineeName='" + nomineeName + '\'' +
                ", balance=" + balance +
                ", isActive=" + isActive +
                ", accountOpenDate=" + accountOpenDate +
                ", identificationNumber=" + identificationNumber +
                '}';
    }


}
