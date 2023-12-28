package com.example.demo2608.service.customer;

import com.example.demo2608.model.customer.Customer_type;
import com.example.demo2608.repository.customer.ICustomerTypeRepos;
import com.example.demo2608.service.ITypeService;
import com.example.demo2608.service.interface_business.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService {

@Autowired
    ICustomerTypeRepos repos;

    @Override
    public List<Customer_type> findAll() {
        return repos.findAll();
    }
}
