package com.example.demo2608.repository.room;


import com.example.demo2608.model.reservation.room.Room_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomTypeRepos extends JpaRepository<Room_type, Integer> {

    @Query("SELECT roty.id, SUM(bode.quantity) as total FROM Booking AS boo " +
            "JOIN Booking_detail AS bode ON boo.id = bode.booking.id AND boo.status='on' " +
            "JOIN Room_type AS roty ON roty.id = bode.room_type.id " +
            "WHERE (boo.check_in <= ?1 AND boo.check_out >= ?2) OR (" +
            "boo.check_in >= ?1 AND boo.check_in < ?2) OR " +
            "(boo.check_in <= ?1 AND boo.check_out > ?1) GROUP BY roty.id")
    List<Object[]> roomIsReserved(String checkIn, String checkOut);

//    @Query("SELECT roty.id, SUM(bode.quantity) as total FROM Booking AS boo " +
//            "JOIN Booking_detail AS bode ON boo.id = bode.booking.id AND boo.status='on' " +
//            "JOIN Room_type AS roty ON roty.id = bode.room_type.id " +
//            "WHERE (boo.check_in <= ?1 AND boo.check_out >= ?2) OR (" +
//            "boo.check_in >= ?1 AND boo.check_in < ?2) OR " +
//            "(boo.check_in <= ?1 AND boo.check_out > ?1) GROUP BY roty.id")
//    List<Object[]> roomIsTest(String checkIn, String checkOut);

}
