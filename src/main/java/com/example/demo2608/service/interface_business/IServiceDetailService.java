package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.reservation.service.Service_detail;
import com.example.demo2608.service.ITypeService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiceDetailService extends ITypeService<Service_detail> {
    List<Service_detail> findAllByBooking_id(Long booking_id);

    void save(Service_detail service_detail);
}
