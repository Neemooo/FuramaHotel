package com.example.demo2608.controller;


import com.example.demo2608.model.dto.Cart;
import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.dto.ServiceDTO;
import com.example.demo2608.model.reservation.room.Room_type;
import com.example.demo2608.model.reservation.service.Service;
import com.example.demo2608.service.service.ServicesService;
import com.example.demo2608.util.SearchError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/service")
public class ServicesController {

    @Autowired
    ServicesService servicesService;

    @RequestMapping("")
    public String findAll(Model model){
        model.addAttribute("list",servicesService.findAll());
        return "services/index";
    }

    @RequestMapping("/add/{id}")
    public String addProduct(@PathVariable Integer id,
                             HttpSession session, Model model) throws ParseException {

        List<ServiceDTO> services=new ArrayList<>();

        Optional<Service> service=servicesService.findById(id);

        if(session.getAttribute("service") == null && service.isPresent()){
            session.setAttribute("service",services);
            services.add(new ServiceDTO(service.get(), 1));
        } else {
            services= (List<ServiceDTO>) session.getAttribute("service");
            int index=this.isExisting(id,services);
            if(index<0){
                services.add(new ServiceDTO(service.get(),1));
            } else {
                services.get(index).setQuantity(services.get(index).getQuantity()+1);
            }
        }

        int totalDate= SearchError.totalDateBooking(session);
        List<Cart> carts= (List<Cart>) session.getAttribute("cart");
        double totalAmount = (carts.stream()
                .mapToDouble(item -> item.getRoomType().getPrice() * item.getQuantity())
                .sum())*totalDate;

        double amountOfService=services.stream()
                .mapToDouble(item -> item.getService().getPrice() * item.getQuantity())
                .sum();

        session.setAttribute("amountOfService",amountOfService);

        model.addAttribute("services",services);
        model.addAttribute("list",carts);
        model.addAttribute("totalDate",totalDate);
        model.addAttribute("total",totalAmount+amountOfService);
        return "/cart/index";

    }

    @RequestMapping("/delete/{id}")
    public String removeProduct(@PathVariable Integer id, HttpSession session, Model model) throws ParseException {
        Optional<Service> rervices = servicesService.findById(id);
        List<ServiceDTO> carts = (List<ServiceDTO>) session.getAttribute("service");
        int index = this.isExisting(id, carts);

        int quantity=carts.get(index).getQuantity();
        if(quantity<2){
            carts.remove(index);
        } else {
            carts.get(index).setQuantity(quantity-1);
        }

        CartController cartController=new CartController();

        return cartController.goCart(session,model);
    }

    // search product in list service
    private int isExisting(Integer id, List<ServiceDTO> cart) {
        for(int i=0;i<cart.size();i++){
            if(cart.get(i).getService().getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
}
