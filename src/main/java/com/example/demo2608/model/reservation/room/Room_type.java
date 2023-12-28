package com.example.demo2608.model.reservation.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "room_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "img")
    String img;

    @Column(name = "price")
    Double price;

    @Column(name = "max_people")
    int max_people;

    @Column(name = "num_of_room")
    Integer numOfRoom;

    @Column(name = "bed")
    int bed;

}
