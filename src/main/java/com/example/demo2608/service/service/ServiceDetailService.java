package com.example.demo2608.service.service;

import com.example.demo2608.model.reservation.service.Service_detail;
import com.example.demo2608.repository.service.IServiceDetailRepos;
import com.example.demo2608.service.interface_business.IServiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDetailService implements IServiceDetailService {

    @Autowired
    IServiceDetailRepos repos;

    @Override
    public List<Service_detail> findAll() {
        return repos.findAll();
    }

    @Override
    public List<Service_detail> findAllByBooking_id(Long booking_id) {
        return repos.findAllByBooking_Id(booking_id);
    }

    @Override
    public void save(Service_detail service_detail) {
        repos.save(service_detail);
    }
}
