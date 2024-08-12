package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.TransactionDTO;
import com.techlabs.entity.Customer;

public interface EmailService {
    void sendEmailToAdmins(CustomerDTO customerDTO);

    void sendEmailToCustomer(Customer customer);

    void sendEmailToCustomerOfAccountInactivated(Customer customerId);

    void sendEmailToSender(String email,int receiverAccountNo, int transactionAmount, int addedBalance);

    void sendEmailToReceiver(String email,int senderAccountNo, int transactionAmount, int addedBalance);
}
