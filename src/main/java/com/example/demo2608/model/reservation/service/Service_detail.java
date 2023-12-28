package com.example.demo2608.model.reservation.service;

import com.example.demo2608.model.dto.ServiceDTO;
import com.example.demo2608.model.reservation.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service_detail {
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
    @JoinColumn(name = "service", nullable = false, referencedColumnName = "id")
    Service service;

    public Service_detail(ServiceDTO serviceDTO, Booking booking){
        this.id=null;
        this.quantity=serviceDTO.getQuantity();
        this.service=serviceDTO.getService();
        this.booking=booking;
    }
}
