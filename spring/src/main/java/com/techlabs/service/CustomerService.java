package com.techlabs.service;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.CustomerResponseDTO;
import com.techlabs.dto.LoginResponseDTO;
import com.techlabs.dto.UpdateProfileDTO;

public interface CustomerService {

    CustomerResponseDTO findCustomerById(int customerId);

    CustomerResponseDTO updateCustomerById(int customerId, CustomerDTO customerDTO);


    CustomerResponseDTO findProfile(int customerId);

    CustomerResponseDTO updateProfile(int customerId, UpdateProfileDTO updateProfileDTO);

    LoginResponseDTO updatedCustomerPassword(int customerId, String password);
}
