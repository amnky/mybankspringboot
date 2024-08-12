package com.techlabs.repository;

import com.techlabs.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByAccountNumber(int receiverAccountNumber);

    @Query("SELECT c.identificationNumber FROM Customer c WHERE c.customerId = :customerId")
    int findUniqueIdentityByCustomerId(@Param("customerId")int customerId);

    @Query("SELECT SUM(c.balance) FROM Customer c WHERE c.identificationNumber = :uniqueIdentity")
    int findBalancesByUniqueIdentity(@Param("uniqueIdentity") int uniqueIdentity);
}
