package com.example.demo2608.model.dto;

import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Booking_detail;
import com.example.demo2608.model.reservation.room.Room_type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

//    String check_in;
//    String check_out;
    Long id;

    Room_type roomType;

    Integer quantity;

    Long booking;

    public Cart(Room_type room_type, int i) {
        this.roomType=room_type;
        this.quantity=i;
        this.id=null;
        this.booking=null;
    }

//    public Cart(Room_type room_type, int i, String check_in, String check_out) {
//        this.roomType=room_type;
//        this.quantity=i;
//        this.check_in=check_in;
//        this.check_out=check_out;
//    }

    public Cart(Booking booking){
        this.booking=booking.getId();
    }

    public Cart(Booking_detail booking_detail){
        this.roomType=booking_detail.getRoom_type();
        this.quantity=booking_detail.getQuantity();
        this.id=booking_detail.getId();
        this.booking=booking_detail.getBooking().getId();
    }
}
