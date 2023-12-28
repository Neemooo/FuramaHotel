package com.example.demo2608.service.employee;

import com.example.demo2608.model.employee.type.Division;
import com.example.demo2608.repository.employee.IDivisionRepos;
import com.example.demo2608.service.interface_business.IDivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService implements IDivisionService {
    @Autowired
    IDivisionRepos repos;

    @Override
    public List<Division> findAll() {
        return repos.findAll();
    }
}
