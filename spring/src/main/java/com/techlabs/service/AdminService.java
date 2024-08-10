package com.techlabs.service;

import com.techlabs.dto.CustomerResponseDTO;
import com.techlabs.dto.RegisterDTO;
import com.techlabs.utils.PagedResponse;

public interface AdminService {
    PagedResponse<CustomerResponseDTO> findAllCustomers(int pageNo, int size, String sort, String sortBy, String sortDirection);

    PagedResponse<RegisterDTO> findPendingAccounts(int pageNo, int size, String sort, String sortBy, String sortDirection);

    CustomerResponseDTO verifyAndAddAccount(int customerId);

    void deleteCustomerRequest(int customerId);
}
