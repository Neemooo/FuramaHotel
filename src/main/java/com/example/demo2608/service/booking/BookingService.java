package com.example.demo2608.service.booking;

import com.example.demo2608.model.dto.BookingDTO;
import com.example.demo2608.model.dto.CustomerDTO;
import com.example.demo2608.model.dto.EmployeeDTO;
import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.repository.booking.IBookingRepos;
import com.example.demo2608.service.interface_business.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    @Autowired
    IBookingRepos repos;

    @Override
    public List<Booking> findAll() {
        return repos.findAll();
    }

    @Override
    public Booking save(Booking booking) {
        return repos.save(booking);
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        return repos.findAll(pageable);
    }

    @Override
    public Page<Booking> findAllByCustomerId(String customer_id, Pageable pageable) {
        return null;
    }

    @Override
    public List<Booking> findAllByCustomerId(String id) {
        return (List<Booking>) repos.findAllByCustomerId(id);
    }

    @Override
    public Optional<Booking> findBookingByCustomer(BookingDTO bookingDTO) {
        return repos.findBookingByCustomer(bookingDTO.getCheck_in(), bookingDTO.getCheck_out(), bookingDTO.getCustomer_id());
    }

    @Override
    public void updateById(Long id_delete) {
        repos.updateById(id_delete);
    }

    @Override
    public Optional<Booking> findBookingById(Long id) {
        return repos.findById(id);
    }

}
