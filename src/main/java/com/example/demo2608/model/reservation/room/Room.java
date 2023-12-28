package com.example.demo2608.model.reservation.room;

import com.example.demo2608.model.employee.type.Division;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Room {
    @Id
    @Column(name = "id")
    private  int id;

    @ManyToOne
    @JoinColumn(name = "room_type", nullable = false, referencedColumnName = "id")
    private Room_type room_type;

    @Column(name = "available")
    private  String available;
}
