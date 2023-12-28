package com.example.demo2608.repository.booking;

import ch.qos.logback.core.boolex.EvaluationException;
import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.reservation.booking.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface IBookingRepos extends JpaRepository<Booking, Long> {

    @Query(value = "select * from booking where customer=?1 and status='on'", nativeQuery = true)
    List<Booking> findAllByCustomerId(String customer_id);


    @Query(value= "select * from booking where check_in=?1 and check_out=?2 and customer=?3 and status ='on' and date=curdate()",nativeQuery =  true)
    Optional<Booking> findBookingByCustomer(String check_in, String check_out, String id);

    @Modifying
    @Transactional
    @Query(value = "update Booking b set b.status = 'off' where b.id = :id_delete", nativeQuery = true)
    void updateById(@Param("id_delete") Long id_delete);
}
