package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.dto.BookingDTO;
import com.example.demo2608.model.dto.CustomerDTO;
import com.example.demo2608.model.dto.EmployeeDTO;
import com.example.demo2608.model.reservation.booking.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;


public interface IBookingService {

    List<Booking> findAll();

    Booking save(Booking booking);

    Page<Booking> findAll(Pageable pageable);

    Page<Booking> findAllByCustomerId(String customer_id, Pageable pageable);

    List<Booking> findAllByCustomerId(String id);

    Optional<Booking> findBookingByCustomer(BookingDTO bookingDTO);

    public void updateById(Long id_delete);

    Optional<Booking> findBookingById(Long id);

}
