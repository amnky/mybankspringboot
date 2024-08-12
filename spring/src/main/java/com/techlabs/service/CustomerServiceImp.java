package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.CustomerResponseDTO;
import com.techlabs.dto.UpdateProfileDTO;
import com.techlabs.entity.Credential;
import com.techlabs.entity.Customer;
import com.techlabs.exception.CustomerApiException;
import com.techlabs.exception.CustomerNotFoundException;
import com.techlabs.repository.AuthRepository;
import com.techlabs.repository.CustomerRepository;
import com.techlabs.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

@Service
public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final AuthRepository authRepository;

    public CustomerServiceImp(CustomerRepository customerRepository,
                              TransactionRepository transactionRepository,
                              AuthRepository authRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.authRepository = authRepository;
    }

    @Override
    public CustomerResponseDTO findCustomerById(int customerId) {
        checkAccess(customerId);
//        generate error in null part
        Customer customer= customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("customer with id was not found"+customerId));
        return CustomerToDTO(customer);

    }

    @Override
    public CustomerResponseDTO updateCustomerById(int customerId, CustomerDTO customerDTO) {
        checkAccess(customerId);
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("customer with id was not found"+customerId));
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
        Customer customer=customerRepository.findById(customerId).
                orElseThrow(() -> new CustomerNotFoundException("customer with id was not found"+customerId));
        return CustomerToDTO(customer);
    }

    @Override
    public CustomerResponseDTO updateProfile(int customerId, UpdateProfileDTO updateProfileDTO) {
        checkAccess(customerId);
        CustomerDTO customerDTO=new CustomerDTO(updateProfileDTO.getFirstName(),updateProfileDTO.getLastName(),
                updateProfileDTO.getNomineeName(),updateProfileDTO.getAddress(),updateProfileDTO.getEmail());
       return updateCustomerById(customerId,customerDTO);
    }
    private void checkAccess(int customerId){
        String customerLoginId = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"));
        if(!customerLoginId.equals(String.valueOf(customerId)) && hasUserRole){
            throw  new ResourceAccessException("you haven't access to this resource, please contact admin");
        }
    }

    @Override
    public void updatedCustomerPassword(int customerId, String password) {
        checkAccess(customerId);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        Credential credential =authRepository.findByCustomerId(customerId);
        credential.setPassword(encodedPassword);
        authRepository.save(credential);
    }


    private CustomerResponseDTO CustomerToDTO(Customer customer) {
        return new CustomerResponseDTO(customer.getFirstName(),customer.getLastName(),
                customer.getAccountNumber(),customer.getBalance());
    }
}
