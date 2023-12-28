package com.example.demo2608.model.dto;

import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Booking_detail;
import com.example.demo2608.model.reservation.room.Room_type;
import com.example.demo2608.model.reservation.service.Service;
import com.example.demo2608.model.reservation.service.Service_detail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    Long id;

    Service service;

    Integer quantity;

    Long booking;

    public ServiceDTO(Service service, int i) {
        this.service=service;
        this.quantity=i;
        this.id=null;
        this.booking=null;
    }

    public ServiceDTO(Booking booking){
        this.booking=booking.getId();
    }

    public ServiceDTO(Service_detail service_detail){
        this.service=service_detail.getService();
        this.quantity=+service_detail.getQuantity();
        this.id=service_detail.getId();
        this.booking=service_detail.getBooking().getId();
    }
}
