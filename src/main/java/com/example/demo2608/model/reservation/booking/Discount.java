package com.example.demo2608.model.reservation.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "discount")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Discount {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "person-generator")
    @GenericGenerator(name = "person-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "CODE-"),
            strategy = "com.example.demo2608.util.PersonAutoGenerator")
    private  String id;

    @Column(name = "name")
    private  String name;

    @Column(name = "beginning_date")
    private String  beginning_date;

    @Column(name = "expiration_date")
    private String  expiration_date;

    @Column(name = "amount")
    private Double amount;
}
