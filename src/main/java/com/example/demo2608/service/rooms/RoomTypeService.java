package com.example.demo2608.service.rooms;

import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.reservation.room.Room_type;
import com.example.demo2608.repository.room.IRoomRepos;
import com.example.demo2608.repository.room.IRoomTypeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomTypeService {

    @Autowired
    IRoomTypeRepos repos;

    public List<Room_type> findAll(){
        return repos.findAll();
    }

    public Optional<Room_type> findById(Integer id){
        return  repos.findById(id);
    }


    public List<Room_type> roomsAreNotReserved(String checkIn, String checkOut){

//        Total: rooms aren't reserved.
       List<Object[]> rooms= repos.roomIsReserved(checkIn, checkOut);

       if(rooms == null){
           return repos.findAll();
       }

        Map<Integer, Long> roomMap=new HashMap<>();

        for (Object[] row : rooms) {
            roomMap.put((Integer) row[0], (Long) row[1]);
        }

//                Iterator<Room_type> iterable = repos.findAll().iterator();
//        List<Room_type> list = new ArrayList<>();
//        while (iterable.hasNext()){
//            Room_type value= iterable.next();
//            if (roomMap.containsKey(value.getId())) {
////                rooms (aren't reserved) = total rooms of room_type - rooms (are reserved).
//                value.setNumOfRoom((value.getNumOfRoom() - Integer.parseInt(String.valueOf(roomMap.get(value.getId())))));
//                if(value.getNumOfRoom()<1){
//                     iterable.remove();
//                     continue;
//                }
//            }
//            list.add(value);
//        }
//
//        return list;
        List<Room_type> availableRooms = repos.findAll().stream()
            .filter(room -> {
                if (roomMap.containsKey(room.getId())) {
                    int reservedCount = Integer.parseInt(String.valueOf(roomMap.get(room.getId())));
                    room.setNumOfRoom(room.getNumOfRoom() - reservedCount);
                    return room.getNumOfRoom() > 0;
                }
                return true;
            })
            .collect(Collectors.toList());

    return availableRooms;
    }

//    list rooms that have max_people are suitable to condition:

    public List<Room_type> findRoomsByPeople(List<Room_type> roomTypes, int people){
        if(people==1) return roomTypes;
        return roomTypes.stream()
                .filter(room -> room.getMax_people() <= people)
                .collect(Collectors.toList());
    }

}
