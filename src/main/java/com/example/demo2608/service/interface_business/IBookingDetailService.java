package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Booking_detail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sun.rmi.runtime.Log;

import java.util.List;

public interface IBookingDetailService {
    List<Booking_detail> findAll();

    Booking_detail save(Booking_detail booking_detail);

    Page<Booking_detail> findAll(Pageable pageable);

    List<Booking_detail> findAllByBookingId(Long booking_id);
}
