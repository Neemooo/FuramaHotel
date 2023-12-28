package com.example.demo2608.controller;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.customer.Customer_type;
import com.example.demo2608.model.dto.CustomerDTO;
import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.reservation.room.Room;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.service.ITypeService;
import com.example.demo2608.service.account.RolesService;
import com.example.demo2608.service.customer.CustomerService;
import com.example.demo2608.service.interface_business.IAccountBankingService;
import com.example.demo2608.service.interface_business.IAccountService;
import com.example.demo2608.service.rooms.RoomService;
import com.example.demo2608.service.rooms.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    @Autowired
    RoomTypeService roomTypeService;


    @RequestMapping("")
    public String viewAllRoom(Model model){
        model.addAttribute("rooms",roomService.findAll());
        model.addAttribute("list",roomTypeService.findAll());
        return "roomType/map";
    }

    @RequestMapping("/updateOn/{id}")
    public String updateOnById(@PathVariable int id, Model model){
        roomService.updateOnById(id);
        return "redirect:/room";
    }

    @RequestMapping("/updateOff/{id}")
    public String updateOffById(@PathVariable int id, Model model){
        roomService.updateOffById(id);
        return "redirect:/room";
    }


}
