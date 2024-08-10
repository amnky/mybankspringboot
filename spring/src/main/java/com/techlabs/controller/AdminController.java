package com.techlabs.controller;

import com.techlabs.dto.CustomerDTO;
import com.techlabs.dto.CustomerResponseDTO;
import com.techlabs.dto.RegisterDTO;
import com.techlabs.dto.TransactionResponseDTO;
import com.techlabs.service.AdminService;
import com.techlabs.service.CustomerService;
import com.techlabs.service.TransactionService;
import com.techlabs.utils.PagedResponse;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final TransactionService transactionService;
    private final CustomerService customerService;

    public AdminController(AdminService adminService, TransactionService transactionService, CustomerService customerService) {
        this.adminService = adminService;
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    @GetMapping("/transactions")
    ResponseEntity<PagedResponse<TransactionResponseDTO>> allTransactions(@RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                                          @RequestParam(name="size",defaultValue = "10") int size,
                                                                          @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                                          @RequestParam(name="sortBy",defaultValue = "transactionTime") String sortBy,
                                                                          @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<TransactionResponseDTO> transactions=transactionService.findAllTransactions(pageNo,size,sort,sortBy, sortDirection);
        return new ResponseEntity<>(transactions, HttpStatus.OK);

    }
    @GetMapping("/transactions/{id}")
    ResponseEntity<PagedResponse<TransactionResponseDTO>> transactionById(@PathVariable("id") int customerId,
                                                                          @RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                                          @RequestParam(name="size",defaultValue = "10") int size,
                                                                          @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                                          @RequestParam(name="sortBy",defaultValue = "transactionTime") String sortBy,
                                                                          @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<TransactionResponseDTO> transactions=transactionService.findAllTransactionsByCustomerId(customerId,pageNo,size,sort,sortBy, sortDirection);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/download-transactions")
    public ResponseEntity<Resource> downloadTransactions(@RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                         @RequestParam(name="size",defaultValue = "10") int size,
                                                         @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                         @RequestParam(name="sortBy",defaultValue = "transactionTime") String sortBy,
                                                         @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection) {
        PagedResponse<TransactionResponseDTO> transactions = transactionService.findAllTransactions(pageNo,size,sort,sortBy, sortDirection); // Fetch data
        List<TransactionResponseDTO> transactionResponseDTOList=transactions.getPagedData();
        // Generate file (using your file generation service/helper)
        ByteArrayInputStream fileInputStream = transactionService.generateXlsxFile(transactionResponseDTOList);

        // Prepare response
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.xlsx");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(fileInputStream));
    }


    @GetMapping("/customers")
    ResponseEntity<PagedResponse<CustomerResponseDTO>> allCustomers(@RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                                    @RequestParam(name="size",defaultValue = "10") int size,
                                                                    @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                                    @RequestParam(name="sortBy",defaultValue = "firstName") String sortBy,
                                                                    @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<CustomerResponseDTO> transactions=adminService.findAllCustomers(pageNo,size,sort,sortBy,sortDirection);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    ResponseEntity<CustomerResponseDTO> customerById(@PathVariable("id") int customerId){
        CustomerResponseDTO customerResponseDTO=customerService.findCustomerById(customerId);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/customer/auth/{id}")
    ResponseEntity<HttpStatus> updatedCustomerPassword(@PathVariable("id") int customerId,@RequestBody String password){
        customerService.updatedCustomerPassword(customerId,password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/customers/{id}")
    ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable("id") int customerId,
                                                       @RequestBody CustomerDTO customerDTO
    ){
        CustomerResponseDTO customerResponseDTO=customerService.updateCustomerById(customerId,customerDTO);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    ResponseEntity<HttpStatus> deleteCustomerById(@PathVariable("id") int customerId){
        adminService.deleteCustomerRequest(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pending-accounts")
    ResponseEntity<PagedResponse<RegisterDTO>> pendingAccounts(@RequestParam(name="pageNo",defaultValue = "0") int pageNo,
                                                               @RequestParam(name="size",defaultValue = "10") int size,
                                                               @RequestParam(name="sort",defaultValue = "ASC") String sort,
                                                               @RequestParam(name="sortBy",defaultValue = "firstName") String sortBy,
                                                               @RequestParam(name="sortDirection",defaultValue = "ASC") String sortDirection){
        PagedResponse<RegisterDTO> accountsInReview=adminService.findPendingAccounts(pageNo,size,sort,sortBy,sortDirection);
        return new ResponseEntity<>(accountsInReview,HttpStatus.OK);
    }

    @PostMapping("/pending-accounts/{id}")
    ResponseEntity<CustomerResponseDTO> verifyAndAddAccount(@PathVariable("id") int registeredId){
    CustomerResponseDTO customerResponseDTO=adminService.verifyAndAddAccount(registeredId);
        return new ResponseEntity<>(customerResponseDTO,HttpStatus.OK);
    }
    @DeleteMapping("/pending-accounts/{id}")
    ResponseEntity<HttpStatus> deleteCustomerRequest(@PathVariable("id") int customerId){
        adminService.deleteCustomerRequest(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
