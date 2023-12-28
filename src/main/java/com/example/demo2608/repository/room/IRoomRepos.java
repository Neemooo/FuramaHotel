package com.example.demo2608.repository.room;

import com.example.demo2608.model.reservation.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IRoomRepos extends JpaRepository<Room, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update Room r set r.available = 'on' where r.id = :id", nativeQuery = true)
    void updateOnById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value = "update Room r set r.available = 'off' where r.id = :id", nativeQuery = true)
    void updateOffById(@Param("id") int id);
}
