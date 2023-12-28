package com.example.demo2608.service.customer;

import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.repository.customer.ICustomerRepos;
import com.example.demo2608.service.interface_business.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    ICustomerRepos repos;



    @Override
    public List<Customer> findAll() {
        return repos.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return repos.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repos.findAll(pageable);
    }

    @Override
    public Customer findByStringId(String id) {
        return repos.findById(id);
    }

    @Override
    public void deleteByStringId(String id) {
        repos.deleteById(id);
    }

    @Override
    public Page<Customer> findByName(String name, Pageable pageable) {
        return repos.findByNameContaining(name, pageable);
    }

    @Override
    public Optional<Customer> findCustomerByUsername(String username) {
        return repos.findByAccount_Username(username);
    }

    @Override
    public Page<Customer> findCustomerByNameContainingOrPhoneOrId_card(String search, Pageable pageable) {
        return repos.findCustomerBySearch(search, pageable);
    }

    @Override
    public void updateById(String id_delete) {
        repos.updateById(id_delete);
    }

    @Override
    public Account findAccountByCustomerId(String id) {
        return repos.findAccountByCustomerId(id);
    }
}
