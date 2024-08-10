package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.CustomerResponseDTO;
import com.techlabs.dto.RegisterDTO;
import com.techlabs.entity.*;
import com.techlabs.repository.*;
import com.techlabs.utils.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final RegisteredRepository registeredRepository;
    private final AuthRepository authRepository;
    private final InactiveCustomerRepository inactiveCustomerRepository;

    public AdminServiceImp(CustomerRepository customerRepository, AccountRepository
            accountRepository, RegisteredRepository registeredRepository, AuthRepository authRepository,
                           InactiveCustomerRepository inactiveCustomerRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.registeredRepository = registeredRepository;
        this.authRepository = authRepository;
        this.inactiveCustomerRepository = inactiveCustomerRepository;
    }

    @Override
    public PagedResponse<CustomerResponseDTO> findAllCustomers(int pageNo, int size, String sort,
                                                               String sortBy, String sortDirection) {
        checkAdminAccess();
        Sort sorting = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, size, sorting);
        Page<Customer> pagedCustomers = customerRepository.findAll(pageable);
        List<Customer> CustomerList = pagedCustomers.getContent();
        List<CustomerResponseDTO> CustomerResponseDTO = customerResponseListToDTO(CustomerList);

        return new PagedResponse<>(CustomerResponseDTO, pagedCustomers.getNumber(),
                pagedCustomers.getSize(), pagedCustomers.getTotalElements(), pagedCustomers.getTotalPages(),
                pagedCustomers.isLast());
    }

    @Override
    public PagedResponse<RegisterDTO> findPendingAccounts(int pageNo, int size, String sort, String sortBy, String sortDirection) {
        checkAdminAccess();
        Sort sorting = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, size, sorting);
        Page<Registered> pagedCustomers = registeredRepository.findAll(pageable);
        List<Registered> registeredList = pagedCustomers.getContent();
        List<RegisterDTO> CustomerResponseDTO = registeredListToDTO(registeredList);

        return new PagedResponse<>(CustomerResponseDTO, pagedCustomers.getNumber(),
                pagedCustomers.getSize(), pagedCustomers.getTotalElements(), pagedCustomers.getTotalPages(),
                pagedCustomers.isLast());

    }


    private List<RegisterDTO> registeredListToDTO(List<Registered> registeredList) {
        List<RegisterDTO> customerDTOList = new ArrayList<>();
        for (Registered registered : registeredList) {
            customerDTOList.add(new RegisterDTO(registered.getId(), registered.getFirstName(), registered.getLastName(),
                    registered.getNomineeName(), registered.getAddress(), registered.getEmail(),
                    registered.getUniqueIdentificationNumber(), registered.getRoleType()));
        }
        return customerDTOList;
    }

    @Override
    public CustomerResponseDTO verifyAndAddAccount(int registeredId) {
        checkAdminAccess();
        Account account = accountRepository.save(new Account());
        Registered registeredCustomer = registeredRepository.findById(registeredId).orElse(null);
        assert registeredCustomer != null;
        if (registeredCustomer.getRoleType() != null && registeredCustomer.getRoleType().name().equals("ROLE_ADMIN")) {
            return addNewAdmin(registeredCustomer);
        } else {
            Customer customer = addCustomerToAccountTable(registeredCustomer);
            customer.setAccountNumber(account.getAccountNumber());
            customer.setAccountOpenDate(LocalDateTime.now());
            customer.setIdentificationNumber(Integer.parseInt(registeredCustomer.getUniqueIdentificationNumber()));
            Customer generatedCustomer = customerRepository.save(customer);
            addCustomerCredentials(generatedCustomer);
            account = accountRepository.findById(account.getAccountNumber()).orElse(null);
            assert account != null;
            account.setCustomerId(generatedCustomer.getCustomerId());
            account.setUniqueIdentificationNumber(registeredCustomer.getUniqueIdentificationNumber());
            accountRepository.save(account);
            registeredRepository.delete(registeredCustomer);
            return customerResponseToDTO(generatedCustomer);
        }
    }

    private void addCustomerCredentials(Customer generatedCustomer) {
        Credential credential = new Credential();
        credential.setCustomerId(generatedCustomer.getCustomerId());
        credential.setRole(Credential.Role.ROLE_CUSTOMER);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(String.valueOf(generatedCustomer.getCustomerId()));
        credential.setPassword(String.valueOf(encodedPassword));
        authRepository.save(credential);
    }

    private void addAdminCredentials(String uniqueIdentificationNumber) {
        Credential credential = new Credential();
        credential.setCustomerId(Integer.parseInt(uniqueIdentificationNumber));
        credential.setRole(Credential.Role.ROLE_ADMIN);
        credential.setPassword(uniqueIdentificationNumber);
        authRepository.save(credential);
    }

    private Customer addCustomerToAccountTable(Registered registeredCustomer) {
        Customer customer = new Customer();
        customer.setCustomerAddress(registeredCustomer.getAddress());
        customer.setLastName(registeredCustomer.getLastName());
        customer.setFirstName(registeredCustomer.getFirstName());
        customer.setNomineeName(registeredCustomer.getNomineeName());
        customer.setEmail(registeredCustomer.getEmail());
        return customer;
    }

    private CustomerResponseDTO addNewAdmin(Registered registeredAdmin) {
        String adminCustomerId = registeredAdmin.getUniqueIdentificationNumber();
        addAdminCredentials(adminCustomerId);
        return new CustomerResponseDTO(registeredAdmin.getFirstName(), registeredAdmin.getLastName(),
                registeredAdmin.getRoleType().name(), Integer.parseInt(adminCustomerId));
    }

    @Override
    public void deleteCustomerRequest(int customerId) {
        checkAdminAccess();
        addCustomerToInactive(customerId);
        customerRepository.deleteById(customerId);
        Credential credential =authRepository.findByCustomerId(customerId);
        authRepository.delete(credential);
    }

    private void addCustomerToInactive(int customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        assert customer != null;
        InactiveCustomer inactiveCustomer = convertCustomerToInactiveCustomer(customer);
        inactiveCustomerRepository.save(inactiveCustomer);
    }

    private InactiveCustomer convertCustomerToInactiveCustomer(Customer customer) {
        return new InactiveCustomer(customer.getCustomerId(), customer.getAccountNumber(), customer.getFirstName(),
                customer.getLastName(), customer.getEmail(), customer.getAccountType(), customer.getCustomerAddress(), customer.getNomineeName(),
                customer.getBalance(), customer.getAccountOpenDate(), customer.getIdentificationNumber());
    }

    private CustomerResponseDTO customerResponseToDTO(Customer customer) {
        return new CustomerResponseDTO(customer.getFirstName(), customer.getLastName(),
                customer.getAccountNumber(), customer.getBalance(),customer.getCustomerId());
    }

    private List<CustomerDTO> customerListToDTO(List<Customer> customerList) {
        List<CustomerDTO> customerResponseDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerResponseDTOList.add(new CustomerDTO(customer.getFirstName(), customer.getLastName(),
                    customer.getNomineeName(), customer.getCustomerAddress()));
        }
        return customerResponseDTOList;
    }

    private List<CustomerResponseDTO> customerResponseListToDTO(List<Customer> customerList) {
        List<CustomerResponseDTO> customerResponseDTOList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerResponseDTOList.add(customerResponseToDTO(customer));
        }
        return customerResponseDTOList;
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
    private void checkAdminAccess(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CUSTOMER"));
        if(hasUserRole){
            throw  new ResourceAccessException("you haven't access to this resource, please contact admin");
        }
    }
}
