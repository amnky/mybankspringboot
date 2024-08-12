package com.techlabs.controller;


import com.techlabs.dto.*;
import com.techlabs.service.CustomerService;
import com.techlabs.service.TransactionService;
import com.techlabs.utils.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final TransactionService transactionService;

    public CustomerController(CustomerService customerService, TransactionService transactionService) {
        this.customerService = customerService;
        this.transactionService = transactionService;
    }

    @GetMapping("/profile/{id}")
    ResponseEntity<CustomerResponseDTO> getProfile(@PathVariable("id")int customerId){
        CustomerResponseDTO customerResponseDTO=customerService.findProfile(customerId);
        return  new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @PostMapping("/profile/{id}")
    ResponseEntity<CustomerResponseDTO> updateProfile(@PathVariable("id")int customerId,
                                                      @Valid @RequestBody UpdateProfileDTO updateProfileDTO){
        CustomerResponseDTO customerResponseDTO=customerService.updateProfile(customerId,updateProfileDTO);
        return  new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }
    @GetMapping("/transactions/{id}")
    ResponseEntity<PagedResponse<TransactionResponseDTO>> getTransactions(@PathVariable("id") int customerId,
                                                                          @RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                                          @RequestParam(name="size",defaultValue = "10") int size,
                                                                          @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                                          @RequestParam(name="sortBy",defaultValue = "transactionTime") String sortBy,
                                                                          @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<TransactionResponseDTO> transactionDTO=transactionService.findAllTransactionsByCustomerId(customerId,pageNo,size,sort,sortBy,sortDirection);
        return new ResponseEntity<>(transactionDTO,HttpStatus.OK);
    }
    @PostMapping("/transactions/{id}")
    ResponseEntity<TransferResponseDTO>performTransaction(@PathVariable("id") int customerId,
                                                          @Valid @RequestBody TransactionDTO transactionDTO){

        TransferResponseDTO transferResponseDTO=transactionService.performTransaction(customerId,transactionDTO);
        return new ResponseEntity<>(transferResponseDTO,HttpStatus.OK);
    }
    @GetMapping("/accounts-balances/{cid}")
    ResponseEntity<Integer>allBalances(@PathVariable("cid")int customerId){
        int allbalance=transactionService.allAccountBalances(customerId);
        return new ResponseEntity<Integer>(allbalance,HttpStatus.OK);
    }
    @PostMapping("/auth/{id}")
    ResponseEntity<HttpStatus> updatedCustomerPassword(@PathVariable("id") int customerId,@RequestBody String password){
        customerService.updatedCustomerPassword(customerId,password);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
