package com.example.demo2608.service.rooms;

import com.example.demo2608.model.reservation.room.Room;
import com.example.demo2608.repository.room.IRoomRepos;
import com.example.demo2608.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements ITypeService<Room> {
    @Autowired
    IRoomRepos roomRepos;

    @Override
    public List<Room> findAll() {
        return roomRepos.findAll();
    }

    public void updateOnById(int id){
        roomRepos.updateOnById(id);
    }

    public void updateOffById(int id){
        roomRepos.updateOffById(id);
    }

//    public String NumberOfEmptyRoom(Integer id){
//        return roomRepos.findEmptyRoom(id);
//    }

}
