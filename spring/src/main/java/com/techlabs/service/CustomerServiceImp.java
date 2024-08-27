package com.techlabs.service;

import com.techlabs.dto.*;
import com.techlabs.entity.Credential;
import com.techlabs.entity.Customer;
import com.techlabs.exception.CustomerNotFoundException;
import com.techlabs.repository.AuthRepository;
import com.techlabs.repository.CustomerRepository;
import com.techlabs.repository.TransactionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;
    private final AuthenticationService authenticationService;
    private final TransactionRepository transactionRepository;
    private final AuthRepository authRepository;

    public CustomerServiceImp(CustomerRepository customerRepository,
                              AuthenticationService authenticationService, TransactionRepository transactionRepository,
                              AuthRepository authRepository) {
        this.customerRepository = customerRepository;
        this.authenticationService = authenticationService;
        this.transactionRepository = transactionRepository;
        this.authRepository = authRepository;
    }

    @Override
    public CustomerResponseDTO findCustomerById(int customerId) {
        checkAccess(customerId);
//        generate error in null part
        Customer customer= customerRepository.findByCustomerIdAndIsActiveTrue(customerId);
        if(customer==null){
            throw new CustomerNotFoundException("customer not found");
        }
        return CustomerToDTO(customer);

    }

    @Override
    public CustomerResponseDTO updateCustomerById(int customerId, CustomerDTO customerDTO) {
        checkAccess(customerId);
        Customer customer=customerRepository.findByCustomerIdAndIsActiveTrue(customerId);
        if(customer==null){
            throw new CustomerNotFoundException("customer not found");
        }
        customer.setCustomerAddress(customerDTO.getAddress());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setNomineeName(customerDTO.getNomineeName());
        Customer updatedCustomer=customerRepository.save(customer);
        return CustomerToDTO(updatedCustomer);
    }



    @Override
    public CustomerResponseDTO findProfile(int customerId) {
        checkAccess(customerId);
        Customer customer=customerRepository.findByCustomerIdAndIsActiveTrue(customerId);
        if(customer==null){
            throw new CustomerNotFoundException("customer not found");
        }
        return CustomerToDTO(customer);
    }

    @Override
    public CustomerResponseDTO updateProfile(int customerId, UpdateProfileDTO updateProfileDTO) {
        System.out.println("inside update service"+customerId);
        checkAccess(customerId);
        CustomerDTO customerDTO=new CustomerDTO(updateProfileDTO.getFirstName(),updateProfileDTO.getLastName(),
                updateProfileDTO.getNomineeName(),updateProfileDTO.getAddress(),updateProfileDTO.getEmail());
       return updateCustomerById(customerId,customerDTO);
    }
    private void checkAccess(int customerId){
        String customerLoginId = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasCustomerRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"));
        if(!customerLoginId.equals(String.valueOf(customerId)) && hasCustomerRole){
            throw  new ResourceAccessException("you haven't access to this resource, please contact admin");
        }
    }

    @Override
    public LoginResponseDTO updatedCustomerPassword(int customerId, String password) {
        checkAccess(customerId);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        Credential credential =authRepository.findByCustomerId(customerId);
        credential.setPassword(encodedPassword);
        authRepository.save(credential);
        return authenticationService.loginUser(new LoginDTO(String.valueOf(customerId),password));
    }


    private CustomerResponseDTO CustomerToDTO(Customer customer) {
        return new CustomerResponseDTO(customer.getFirstName(),customer.getLastName(),
                customer.getAccountNumber(),customer.getBalance(),customer.getCustomerId(),
                customer.getNomineeName(),customer.getCustomerAddress(),customer.getEmail());
    }
}
