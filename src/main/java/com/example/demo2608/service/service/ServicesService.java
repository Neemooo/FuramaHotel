package com.example.demo2608.service.service;

import com.example.demo2608.model.reservation.service.Service;
import com.example.demo2608.repository.service.IServiceRepos;
import com.example.demo2608.service.interface_business.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServicesService implements IServiceService {

    @Autowired
    IServiceRepos repos;

    @Override
    public List<Service> findAll() {
        return repos.findAll();
    }


    @Override
    public Optional<Service> findById(Integer id) {
        return repos.findById(id);
    }

}
