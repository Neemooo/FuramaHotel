package com.example.demo2608.model.reservation.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "name")
    private  String name;

    @Column(name = "img")
    String img;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private  double price;

    @Column(name = "status")
    private  String status;
}
