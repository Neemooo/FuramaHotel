package com.example.demo2608.repository.customer;

import com.example.demo2608.model.customer.Customer_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerTypeRepos extends JpaRepository<Customer_type, Integer> {
}
