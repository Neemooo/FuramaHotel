package com.example.demo2608.controller;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.booking.Booking_detail;
import com.example.demo2608.model.reservation.service.Service_detail;
import com.example.demo2608.repository.room.IRoomTypeRepos;
import com.example.demo2608.repository.service.IServiceDetailRepos;
import com.example.demo2608.service.interface_business.IAccountBankingService;
import com.example.demo2608.service.interface_business.IBookingDetailService;
import com.example.demo2608.service.interface_business.IBookingService;
import com.example.demo2608.service.interface_business.ICustomerService;
import com.example.demo2608.service.mail.MailHelperService;
import com.example.demo2608.util.GmailWithInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/booking")
public class BookingController {

    final int MAX_DISPLAY = 10;

    @Autowired
    ICustomerService customerService;

    @Autowired
    IBookingService bookingService;

    @Autowired
    IBookingDetailService bookingDetailService;

    @Autowired
    IRoomTypeRepos roomTypeRepos;

    @Autowired
    IServiceDetailRepos serviceDetailRepos;

    @Autowired
    IAccountBankingService accountBankingService;

    @Autowired
    MailHelperService mailHelperService;

    @Autowired
    GmailWithInvoice gmailWithInvoice;

    @RequestMapping("/{id}")
    public String findBookingByCustomerId(@PathVariable String id, Model model){

            List<Booking> bookingList = bookingService.findAllByCustomerId(id);

            if(bookingList!=null){
                List<Booking_detail> booking_details=new ArrayList<>();
                List<Service_detail> service_details=new ArrayList<>();
                for (Booking booking:bookingList) {
//                    find bookingDetails:
                    booking_details=bookingDetailService.findAllByBookingId(booking.getId());
                    model.addAttribute("bookingDetails_"+booking.getId(),booking_details);

//                    find serviceDetails:
                    service_details=serviceDetailRepos.findAllByBooking_Id(booking.getId());
                    model.addAttribute("serviceDetails_"+booking.getId(),service_details);
                }
                model.addAttribute("list", bookingList);
            }
        model.addAttribute("name", customerService.findByStringId(id).getName());
        return "booking/index";
    }

    @RequestMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public String deleteById(@RequestParam("id") Long id, Model model) {

        if(inspectBetweenDateDeleteAndCheckIn(bookingService.findBookingById(id).get())){
            returnMoney(bookingService.findBookingById(id).get(), findCustomer().getAccountBanking());
            mailHelperService.sendMessageWithAttachment(findCustomer().getEmail(),
                    "Bien lai chuyen tien",
                    gmailWithInvoice.getPaymentReceipt(bookingService.findBookingById(id).get(),findCustomer()),
                    "");
        }

        bookingService.updateById(id);

        model.addAttribute("messages","Delete success!!!");

        return findBookingByCustomerId(findCustomer().getId(),model);
    }

    private Customer findCustomer(){
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Customer> optionalCustomer=customerService.findCustomerByUsername(username);
        return optionalCustomer.get();
    }

    private boolean inspectBetweenDateDeleteAndCheckIn(Booking booking){

        LocalDate checkIn = LocalDate.parse(booking.getCheck_in());
        LocalDate currentDate = LocalDate.now();

       return ChronoUnit.DAYS.between(currentDate, checkIn)>=3;
    }

    private void returnMoney(Booking booking,AccountBanking accountBanking){
        accountBanking.setBalance(accountBanking.getBalance()+booking.getAmount()*0.8);
        accountBankingService.save(accountBanking);
    }

}
