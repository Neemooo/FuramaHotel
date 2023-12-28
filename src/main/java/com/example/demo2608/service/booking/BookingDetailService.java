package com.example.demo2608.service.booking;

import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Booking_detail;
import com.example.demo2608.repository.booking.IBookingDetailRepos;
import com.example.demo2608.service.interface_business.IBookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingDetailService implements IBookingDetailService {

    @Autowired
    IBookingDetailRepos repos;

    @Override
    public List<Booking_detail> findAll() {
        return repos.findAll();
    }

    @Override
    public Booking_detail save(Booking_detail booking_detail) {
        return repos.save(booking_detail);
    }

    @Override
    public Page<Booking_detail> findAll(Pageable pageable) {
        return repos.findAll(pageable);
    }

    @Override
    public List<Booking_detail> findAllByBookingId(Long booking_id) {
        return repos.findAllByBookingId(booking_id);
    }
}
