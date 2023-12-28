package com.example.demo2608.model.reservation.booking;

import com.example.demo2608.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transaction_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction_history {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "date")
    private  String date;

    @Column(name = "amount")
    private  Double amount;

    @OneToOne
    @JoinColumn(name = "Customer", nullable = false, referencedColumnName = "id")
    Customer customer;

    public Transaction_history(Booking booking, Customer customer, String detail){
        this.id=null;
        this.name=detail;
        this.date=booking.getDate();
        this.customer=customer;
        this.amount=booking.getAmount();
    }
}
