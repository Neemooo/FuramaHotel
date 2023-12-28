package com.example.demo2608.controller;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.dto.BookingDTO;
import com.example.demo2608.model.dto.Cart;
import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.dto.ServiceDTO;
import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Booking_detail;
import com.example.demo2608.model.reservation.booking.Discount;
import com.example.demo2608.model.reservation.booking.Transaction_history;
import com.example.demo2608.model.reservation.room.Room_type;
import com.example.demo2608.model.reservation.service.Service;
import com.example.demo2608.model.reservation.service.Service_detail;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.repository.booking.IBookingDetailRepos;
import com.example.demo2608.repository.booking.IBookingRepos;
import com.example.demo2608.repository.service.IServiceDetailRepos;
import com.example.demo2608.service.IMailSender;
import com.example.demo2608.service.interface_business.*;
import com.example.demo2608.service.rooms.RoomTypeService;
import com.example.demo2608.util.GmailWithInvoice;
import com.example.demo2608.util.SearchError;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    ICustomerService customerService;

    @Autowired
    IDiscountService discountService;

    @Autowired
    IBookingService bookingService;

    @Autowired
    IBookingDetailService bookingDetailService;

    @Autowired
   IAccountBankingService accountBankingService;

    @Autowired
    IServiceDetailService serviceDetailService;

    @Autowired
    IMailSender mailSender;

    @Autowired
    GmailWithInvoice gmailWithInvoice;

    @Autowired
    ITransactionService transactionService;


//    @RequestMapping("")
//    public String goCartPage(){
//        return "cart/index";
//    }

    @RequestMapping("")
    public String goCart(HttpSession session,Model model) throws ParseException {
        if(session.getAttribute("cart") != null){
           List<Cart> carts= (List<Cart>) session.getAttribute("cart");
            int totalDate= SearchError.totalDateBooking(session);
            double totalAmount=calculateAmount(session);
            if(session.getAttribute("service")!=null){
                List<ServiceDTO> services=(List<ServiceDTO>) session.getAttribute("service");
                model.addAttribute("services",services);
            }
//            double totalAmount = (carts.stream()
//                    .mapToDouble(cart -> cart.getRoomType().getPrice() * cart.getQuantity())
//                    .sum())*totalDate;
//            if(session.getAttribute("service")!=null){
//                List<ServiceDTO> services= (List<ServiceDTO>) session.getAttribute("service");
//                double amountOfService=services.stream()
//                        .mapToDouble(item -> item.getService().getPrice() * item.getQuantity())
//                        .sum();
//                totalAmount=totalAmount+amountOfService;
//            }
            model.addAttribute("list",carts);
            model.addAttribute("totalDate",totalDate);
            model.addAttribute("total",totalAmount);
        }
        return "/cart/index";
    }

//    @RequestMapping("/add/{id}")
//    public String addProduct(@PathVariable Integer id,
//                             @RequestParam("check_in") String check_in,
//                             @RequestParam("check_out") String check_out,
//                             HttpSession session, Model model){
//
//        List<Cart> carts=new ArrayList<>();
//
//        Optional<Room_type> roomType=roomTypeService.findById(id);
//
//        if(session.getAttribute("cart")==null){
//            session.setAttribute("cart",carts);
//            Cart.setCheck_int(check_in);
//            Cart.setCheck_out(check_out);
//            carts.add(new Cart(roomType.get(), 1));
//        } else {
//            carts= (List<Cart>) session.getAttribute("cart");
//            int index=this.isExisting(id,carts);
//            if(index<0){
//                carts.add(new Cart(roomType.get(),1));
//            } else {
//                carts.get(index).setQuantity(carts.get(index).getQuantity()+1);
//            }
//        }
//
//        double totalAmount = carts.stream()
//                .mapToDouble(cart -> cart.getRoomType().getPrice() * cart.getQuantity())
//                .sum();
//
//        model.addAttribute("cart",carts);
//        model.addAttribute("total",totalAmount);
//        return "/cart/index";
//    }

//    add room in booking:
    @RequestMapping("/add/{id}")
    public String addProduct(@PathVariable Integer id,
                             HttpSession session, Model model) {

        List<Cart> carts=new ArrayList<>();

        Optional<Room_type> roomType=roomTypeService.findById(id);

        if(session.getAttribute("cart") == null && roomType.isPresent()){
            session.setAttribute("cart",carts);
            carts.add(new Cart(roomType.get(), 1));
        } else {
            carts= (List<Cart>) session.getAttribute("cart");
            int index=this.isExisting(id,carts);
            if(index<0){
                carts.add(new Cart(roomType.get(),1));
            } else {
                carts.get(index).setQuantity(carts.get(index).getQuantity()+1);
            }
        }

//        int totalDate= SearchError.totalDateBooking(session);
//        double totalAmount = (carts.stream()
//                .mapToDouble(item -> item.getRoomType().getPrice() * item.getQuantity())
//                .sum())*totalDate;

//        model.addAttribute("list",carts);
//        model.addAttribute("totalDate",totalDate);
//        model.addAttribute("total",totalAmount);
//        return "/cart/index";

        FormSearchDTO formSearchDTO= (FormSearchDTO) session.getAttribute("form");

        List<Room_type> room_types= roomTypeService.roomsAreNotReserved(formSearchDTO.getCheckin(), formSearchDTO.getCheckout());

//
//        for (Room_type room : room_types) {
//            if (room.getId().equals(id) && room.getNumOfRoom() > 0) {
//                room.setNumOfRoom(room.getNumOfRoom() - 1);
//            }
//        }

//        for (Cart item : carts) {
//            for (Room_type room : room_types) {
//                if (room.getId().equals(item.getRoomType().getId()) && room.getNumOfRoom()>0) {
//                   room.setNumOfRoom(room.getNumOfRoom() - item.getQuantity());
//                }
//            }
//        }

        carts.forEach(item ->
                room_types.stream()
                        .filter(room -> room.getId().equals(item.getRoomType().getId()) && room.getNumOfRoom() > 0)
                        .findFirst()
                        .ifPresent(room -> room.setNumOfRoom(room.getNumOfRoom() - item.getQuantity()))
        );
        List<Room_type> roomsIsValid = room_types.stream()
                .filter(room -> room.getNumOfRoom() > 0)
                .collect(Collectors.toList());

//        Iterator<Room_type> iterator = room_types.iterator();
//        while (iterator.hasNext()) {
//            Room_type room = iterator.next();
//            if (room.getId().equals(id) && room.getNumOfRoom() > 0) {
//                room.setNumOfRoom(room.getNumOfRoom() - 1);
//                if (room.getNumOfRoom() <= 0) {
//                    iterator.remove(); // Remove the room if numOfRoom becomes 0 or less
//                }
//            }
//        }

//      inspect list of rooms have condition (max_people):
      roomsIsValid= roomTypeService.findRoomsByPeople(roomsIsValid,SearchError.hasErrorPeopleLimit(formSearchDTO));

        model.addAttribute("list", roomsIsValid);

        return "roomType/index";

    }

//    @PostMapping("/cart/add/{id}")
//    @ResponseBody // Chú thích này cho biết kết quả sẽ trả về là text hoặc JSON
//    public String addToCart(@PathVariable Integer id, HttpSession session, Model model) throws ParseException {
//        // Logic thêm sản phẩm vào giỏ hàng ở đây
//        // ...
//
//        return "Product added to cart successfully!"; // Trả về thông báo thành công
//    }

    @RequestMapping("/delete/{id}")
    public String removeProduct(@PathVariable Integer id, HttpSession session, Model model) throws ParseException {
        Optional<Room_type> roomType=roomTypeService.findById(id);
        List<Cart> carts=(List<Cart>) session.getAttribute("cart");
        int index=this.isExisting(id,carts);

//        int totalDate= SearchError.totalDateBooking(session);
        int quantity=carts.get(index).getQuantity();
        if(quantity<2){
            carts.remove(index);
        } else {
            carts.get(index).setQuantity(quantity-1);
        }

        if(carts.isEmpty()){
            return removeSession(session, model);
        }

//        Double totalAmount = carts.stream()
//                .mapToDouble(cart -> cart.getRoomType().getPrice() * cart.getQuantity())
//                .sum();

//        double totalAmount=calculateAmount(session,carts,totalDate);
//        model.addAttribute("list",carts);
//        model.addAttribute()
//        model.addAttribute("total",totalAmount);
//        return "/cart/index";
       return goCart(session,model);
    }

    @RequestMapping("/removeSession")
    public String removeSession(HttpSession session, Model model) throws ParseException {
        session.removeAttribute("cart");
        return goCart(session,model);
    }

//    @RequestMapping("/showList/{carts}")
//    public String showListCart(@PathVariable List<Cart> carts, Model model,HttpSession session){
//        int totalDate= SearchError.totalDateBooking(session);
//        double totalAmount = carts.stream()
//                .mapToDouble(cart -> cart.getRoomType().getPrice() * cart.getQuantity())
//                .sum();
//        model.addAttribute("list",carts);
//        model.addAttribute("total",totalAmount,);
//        return "/cart/index";
//    }

    // search product in list cart
    public int isExisting(Integer id, List<Cart> cart) {
        for(int i=0;i<cart.size();i++){
            if(cart.get(i).getRoomType().getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    @RequestMapping("/reserved")
    public String goToPagesDTO(HttpSession session, Model model) throws ParseException {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            BookingDTO bookingDTO=new BookingDTO();
            bookingDTO.setCheck_in((String) session.getAttribute("check_in"));
            bookingDTO.setCheck_out((String) session.getAttribute("check_out"));

            bookingDTO.setAmount(calculateAmount(session));

            String username= SecurityContextHolder.getContext().getAuthentication().getName();

            Optional<Customer> optionalCustomer=customerService.findCustomerByUsername(username);

            if(optionalCustomer.isPresent()){
                bookingDTO.setCustomer_id(optionalCustomer.get().getId());
                model.addAttribute("name",optionalCustomer.get().getName());
            }
            model.addAttribute("booking",bookingDTO);
            return "/cart/form";
        }

        return "redirect:/login";

    }

    @RequestMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public String isFinishedReservation(@Valid @ModelAttribute("booking") BookingDTO bookingDTO ,Model model, HttpSession session) throws ParseException {
        Customer customer=customerService.findByStringId(bookingDTO.getCustomer_id());

        Booking booking=new Booking(bookingDTO, customer);


//        inspect discount
        String id=bookingDTO.getDiscount();
        if(!StringUtils.isEmpty(id)){
//            inspect discount is valid;
            Optional<Discount> discount=discountService.findValidDiscountByCode(id,booking.getDate());
            if(discount.isPresent()){
                booking.setDiscount(discount.get());
                booking.setAmount(booking.getAmount()*(1-discount.get().getAmount()/100)) ;
            } else {
                model.addAttribute("discount","Can not find discount");
                return goToPagesDTO(session, model);
            }
        }

//        inspect account_banking: balance > total of invoice.
//        AccountBanking accountBanking=customer.getAccountBanking();
        double account_balance=customer.getAccountBanking().getBalance();
        if(account_balance-booking.getAmount()<=10){
            model.addAttribute("account_balance","Account balance is not enough");
            return goToPagesDTO(session, model);
        }

            account_balance=account_balance-booking.getAmount();
            accountBankingService.updateBalanceById(customer.getAccountBanking().getId(),account_balance);


        bookingService.save(booking);

        Optional<Booking> bookingExist=bookingService.findBookingByCustomer(bookingDTO);

//        Booking booking1=bookingExist.get();

        List<Cart> cartList= (List<Cart>) session.getAttribute("cart");
        for (Cart cart : cartList) {
            bookingDetailService.save(new Booking_detail(cart,bookingExist.get()));
        }

        List<ServiceDTO> serviceList= (List<ServiceDTO>) session.getAttribute("service");

        for (ServiceDTO serviceDTO : serviceList){
            serviceDetailService.save(new Service_detail(serviceDTO,bookingExist.get()));
        }


        model.addAttribute("roomList",cartList);
        model.addAttribute("serviceList",serviceList);
        model.addAttribute("booking",bookingExist.get());
        model.addAttribute("name",customer.getName());

        transactionService.save(new Transaction_history(bookingExist.get(), customer, "Tai khoan quy khach bi tru "+bookingExist.get().getAmount()));
        
                mailSender.sendMessageWithAttachment(customer.getEmail(),
                "Đơn hàng của bạn đã đăng kí thành công!",
                    gmailWithInvoice.getContentByString(bookingExist.get(), cartList, serviceList, customer),
                ""
                );

        session.removeAttribute("cart");



        return "cart/invoice";
    }

//    private Booking checkDiscountIsValid(String id, Booking booking){
//        if(!StringUtils.isEmpty(id)){
////            inspect discount is valid;
//            Optional<Discount> discount=discountService.findValidDiscountByCode(id,booking.getDate());
//            if(discount.isPresent()){
//                booking.setDiscount(discount.get());
//                booking.setAmount(booking.getAmount()*(1-discount.get().getAmount()/100)) ;
//            }
//        }
//        return booking;
//    }

//    private boolean checkBalance(HttpSession session){
//        session.getAttribute("username");
//
//    }


    public double calculateAmount(HttpSession session) throws ParseException {
        List<Cart> carts= (List<Cart>) session.getAttribute("cart");
        int totalDate= SearchError.totalDateBooking(session);
//        total amount of room:
        double totalAmount = (carts.stream()
                .mapToDouble(cart -> cart.getRoomType().getPrice() * cart.getQuantity())
                .sum())*totalDate;
        if(session.getAttribute("service")!=null){
            List<ServiceDTO> services= (List<ServiceDTO>) session.getAttribute("service");
            double amountOfService=services.stream()
                    .mapToDouble(item -> item.getService().getPrice() * item.getQuantity())
                    .sum();
            totalAmount=totalAmount+amountOfService;
        }
        return totalAmount;
    }
}
