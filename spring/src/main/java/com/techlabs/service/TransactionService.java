package com.techlabs.service;

import com.techlabs.dto.TransactionDTO;
import com.techlabs.dto.TransactionResponseDTO;
import com.techlabs.dto.TransferResponseDTO;
import com.techlabs.utils.PagedResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface TransactionService {
    PagedResponse<TransactionResponseDTO> findAllTransactions(int pageNo, int size, String sort, String sortBy, String sortDirection);

    PagedResponse<TransactionResponseDTO> findAllTransactionsByCustomerId(int customerId, int pageNo, int size, String sort, String sortBy, String sortDirection);

    TransferResponseDTO performTransaction(TransactionDTO transactionDTO);

    ByteArrayInputStream generateXlsxFile(List<TransactionResponseDTO> transactions);

    int allAccountBalances();
}
