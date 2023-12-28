package com.example.demo2608.controller;

import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.reservation.room.Room_type;
import com.example.demo2608.service.rooms.RoomService;
import com.example.demo2608.service.rooms.RoomTypeService;
import com.example.demo2608.util.SearchError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/roomType")
public class RoomTypeController {

    @Autowired
    RoomTypeService roomTypeService;
    @Autowired
    RoomService roomService;

    @RequestMapping("/view/{id}")
    public String findById(@PathVariable Integer id, Model model){
        Optional<Room_type> roomType=roomTypeService.findById(id);
        model.addAttribute("room",roomType.get());
            return "roomType/view";
    }

    @RequestMapping("/view")
    public String findByAll(Model model, HttpSession session){
        if(session.getAttribute("form")!=null){
            FormSearchDTO formSearchDTO= (FormSearchDTO) session.getAttribute("form");
            List<Room_type> room_types= roomTypeService.roomsAreNotReserved(formSearchDTO.getCheckin(), formSearchDTO.getCheckout());
//      inspect list of rooms have condition (max_people):
            room_types=roomTypeService.findRoomsByPeople(room_types, SearchError.hasErrorPeopleLimit(formSearchDTO));
            model.addAttribute("list",room_types);
        } else {
            model.addAttribute("list",roomTypeService.findAll());
        }

        return "roomType/index";
    }

    @RequestMapping("/map")
    public String viewAllRoom(Model model){
        model.addAttribute("rooms",roomService.findAll());
        model.addAttribute("list",roomTypeService.findAll());
        return "roomType/map";
    }

}
