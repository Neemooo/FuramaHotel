package com.example.demo2608.repository.booking;

import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Booking_detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookingDetailRepos extends JpaRepository<Booking_detail, Long> {

    @Query(value = "select * from booking_detail where booking=?1", nativeQuery = true)
    List<Booking_detail> findAllByBookingId(Long booking_id);

//    List<Booking_detail> findAllByBooking_Id(Long booking_id);
}
