package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.reservation.service.Service;
import com.example.demo2608.service.IBaseService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IServiceService {

    public List<Service> findAll();

    public Optional<Service> findById(Integer id);
}
