package com.example.demo2608.repository.service;

import com.example.demo2608.model.reservation.service.Service_detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IServiceDetailRepos extends JpaRepository<Service_detail,Long> {

    List<Service_detail> findAllByBooking_Id(Long booking_id);
}
