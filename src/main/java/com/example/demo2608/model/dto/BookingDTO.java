package com.example.demo2608.model.dto;

import com.example.demo2608.model.reservation.booking.Booking;
import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    Long id;

    String check_in;

    String check_out;

    String description;

    String customer_id;

    String discount;

    String date;

    Double amount;

    public BookingDTO(Booking booking){
        this.id=booking.getId();
        this.check_in=booking.getCheck_in();
        this.check_out=booking.getCheck_out();
        if(booking.getDescription()!=null){
            this.description=booking.getDescription();
        } else {
            this.description="Nothing";
        }
        if(booking.getDiscount()!=null){
            this.discount=booking.getDiscount().getName();
        } else {
            this.discount="Nothing";
        }
        this.customer_id=booking.getCustomer().getId();
        this.date=booking.getDate();
        this.amount=booking.getAmount();
    }

}
