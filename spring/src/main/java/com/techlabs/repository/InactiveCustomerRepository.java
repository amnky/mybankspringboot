package com.techlabs.repository;

import com.techlabs.entity.InactiveCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InactiveCustomerRepository extends JpaRepository<InactiveCustomer,Integer> {
    InactiveCustomer findByCustomerId(int customerId);
}
