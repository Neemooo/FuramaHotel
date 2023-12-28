package com.example.demo2608.model.reservation.booking;


import com.example.demo2608.model.dto.Cart;
import com.example.demo2608.model.reservation.room.Room_type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
@Table(name = "booking_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking_detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "quantity")
    private  int quantity;

    @ManyToOne
    @JoinColumn(name = "booking", nullable = false, referencedColumnName = "id")
    Booking booking;

    @ManyToOne
    @JoinColumn(name = "room_type", nullable = false, referencedColumnName = "id")
    Room_type room_type;


    public Booking_detail(Cart cart, Booking booking){
        this.id=null;
        this.quantity=cart.getQuantity();
        this.room_type=cart.getRoomType();
        this.booking=booking;
    }

}
