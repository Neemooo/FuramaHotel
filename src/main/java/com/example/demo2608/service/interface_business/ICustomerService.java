package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface ICustomerService extends IBaseService<Customer> {

    Customer findByStringId(String id);

    void deleteByStringId(String id);

    Page<Customer> findByName(String name, Pageable pageable);

    Optional<Customer> findCustomerByUsername(String username);

    Page<Customer> findCustomerByNameContainingOrPhoneOrId_card(String search, Pageable pageable);

    void updateById(String id_delete);

    Account findAccountByCustomerId(String id);

}
