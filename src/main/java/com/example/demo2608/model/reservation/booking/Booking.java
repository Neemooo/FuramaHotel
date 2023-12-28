package com.example.demo2608.model.reservation.booking;

import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.dto.BookingDTO;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false, referencedColumnName = "id")
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "discount", nullable = true, referencedColumnName = "id")
    Discount discount;

    @Column(name = "date")
    private  String date;

    @Column(name = "description")
    private String description;

    @Column(name = "check_in")
    private String check_in;

    @Column(name = "check_out")
    private String check_out;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private String status;

    public Booking(Customer customer, HttpSession session, Double amount){
        this.id=null;
        this.discount=null;
        this.description=null;
        this.customer=customer;
        this.date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.check_in= (String) session.getAttribute("check_in");
        this.check_out= (String) session.getAttribute("check_out");
        this.amount=amount;
        this.status="on";
    }

//    public Booking(BookingDTO bookingDTO, Customer customer){
//        this.id=null;
//        this.discount=null;
//        this.description=null;
//        this.customer=customer;
//        this.date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        this.check_in=bookingDTO.getCheck_in();
//        this.check_out=bookingDTO.getCheck_out();
//        this.amount=bookingDTO.getAmount();
//    }

    public Booking(BookingDTO bookingDTO, Customer customer){
        this.id=null;

        if(!StringUtils.isEmpty(bookingDTO.getDescription())){
            this.description=bookingDTO.getDescription();
        } else {
            this.description=null;
        }

        this.discount=null;
        this.customer=customer;
        this.date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.check_in=bookingDTO.getCheck_in();
        this.check_out=bookingDTO.getCheck_out();
        this.amount=bookingDTO.getAmount();
        this.status="on";
    }

}
