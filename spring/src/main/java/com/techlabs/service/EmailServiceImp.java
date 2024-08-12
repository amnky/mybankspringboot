package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.TransactionDTO;
import com.techlabs.entity.Admins;
import com.techlabs.entity.Customer;
import com.techlabs.exception.EmailNotSendException;
import com.techlabs.repository.AdminRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmailServiceImp implements EmailService{

    private final AdminRepository adminRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImp(AdminRepository adminRepository,JavaMailSender javaMailSender) {
        this.adminRepository = adminRepository;
        this.javaMailSender=javaMailSender;
    }

    @Override
    public void sendEmailToAdmins(CustomerDTO customerDTO) {
        List<String> toEmails = new ArrayList<>();
       List<Admins> allAdmins= adminRepository.findAll();
       for(Admins admin:allAdmins){
           toEmails.add(admin.getAdminEmail());
       }
        String[] emailArray = toEmails.toArray(new String[0]);
        sendMailWithAttachment(customerDTO,emailArray);

    }

    @Override
    public void sendEmailToCustomer(Customer customer) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(customer.getEmail());
            String body= "CustomerId: "+customer.getCustomerId()+"\n"+"AccountNo: "+customer.getAccountNumber()
                    +"\n"+"Balance: "+customer.getBalance()+"\n"+"Account OpeningDate: "+customer.getAccountOpenDate();
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(
                    "Congrats! account has been activated");
            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new EmailNotSendException("can't send email to customer with subject: account activated");
        }

    }

    @Override
    public void sendEmailToCustomerOfAccountInactivated(Customer customer) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(customer.getEmail());
            String body= "your bank admin has inactivated your account\n"+"CustomerId: "+customer.getCustomerId()+"\n"+"AccountNo: "+customer.getAccountNumber()
                    +"\n"+"Balance: "+customer.getBalance()+"\n"+"Account OpeningDate: "+customer.getAccountOpenDate();
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(
                    "account has been de-activated");
            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new EmailNotSendException("can't send email to customer with subject: account inactivated");
        }
    }

    @Override
    public void sendEmailToSender(String email,int ReceiverAccountNo, int transactionAmount, int addedBalance) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email);
            String body= "Transaction amount: "+transactionAmount+"\n"+"Receiver AccountNo : "+ReceiverAccountNo+
                    "\n"+"Your current Balance: "+addedBalance;
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(
                    "Transaction Update");

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new EmailNotSendException("can't send email to customer with subject: Transaction Update");
        }
    }

    @Override
    public void sendEmailToReceiver(String email,int SenderAccountNo, int transactionAmount, int addedBalance) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email);
            String body= "Transaction amount: "+transactionAmount+"\n"+"Sender AccountNo : "+SenderAccountNo+
                    "\n"+"Your current Balance: "+addedBalance;
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(
                    "Transaction Update");

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new EmailNotSendException("can't send email to customer with subject: Transaction Update");
        }
    }


    private void sendMailWithAttachment(CustomerDTO details, String[]adminEmails)
    {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(adminEmails);
            String body= "FirstName: "+details.getFirstName()+"\n"+"lastName: "+details.getLastName()
                    +"\n"+"NomineeName: "+details.getNomineeName()+"\n"+"Email: "+details.getEmail()
                    +"\n"+"Address: "+details.getAddress()+"\n"+"IdentityNumber: "+details.getUniqueIdentificationNumber();
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(
                    "Customer register Request");

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            throw new EmailNotSendException("can't send email to admin with subject: Customer register Request");
        }
    }
}
