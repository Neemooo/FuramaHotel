package com.example.demo2608.controller;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.customer.Customer_type;
import com.example.demo2608.model.dto.CustomerDTO;
import com.example.demo2608.model.dto.EmployeeDTO;
import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.employee.Employee;
import com.example.demo2608.model.reservation.booking.Booking;
import com.example.demo2608.model.reservation.room.Room_type;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.model.role.Role;
import com.example.demo2608.repository.account.IRolesRepos;
import com.example.demo2608.service.account.RolesService;
import com.example.demo2608.service.customer.CustomerService;
import com.example.demo2608.service.ITypeService;
import com.example.demo2608.service.interface_business.IAccountBankingService;
import com.example.demo2608.service.interface_business.IAccountService;
import com.example.demo2608.service.interface_business.IRolesService;
import com.example.demo2608.service.rooms.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    final int MAX_DISPLAY = 10;

    @Autowired
    CustomerService customerService;

    @Autowired
    ITypeService<Customer_type> typeService;

    @Autowired
    IAccountBankingService accountBankingService;

    @Autowired
    IAccountService accountService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    RolesService rolesService;

    // findByName of name customer
    @RequestMapping("")
//    @PreAuthorize("hasRole('admin')")
    public String findByName(Model model, @RequestParam(defaultValue = "") String name_search,
                             @PageableDefault(size = MAX_DISPLAY, sort = "name", direction = Sort.Direction.ASC)
                                     Pageable pageable, HttpSession session) {

//        Page<Customer> customerList=customerService.findByName(name_search, pageable);

        Page<Customer> customerList=customerService.findCustomerByNameContainingOrPhoneOrId_card(name_search, pageable);

        model.addAttribute("list", customerList);
        model.addAttribute("name_search", name_search);

//        model.addAttribute("role","admin");
//        model.addAttribute("username",name_search);

        return "customer/index";
    }

    // transfer customer/form with information of customer for update:
    @RequestMapping("/edit/{id}")
    public String editCustomer(@PathVariable String id, Model model) {
        Optional<Customer> customer = Optional.ofNullable(customerService.findByStringId(id));
        if (customer.isPresent()) {
            model.addAttribute("customer", new CustomerDTO(customer.get()));
            model.addAttribute("typeList", typeService.findAll());
            return "customer/form";
        }
        return "redirect:/";
    }

//    // also transfer customer/form without information of customer because it's create:
//    @RequestMapping("/create")
//    public String createCustomer(Model model, HttpSession session) {
//            model.addAttribute("customer", new CustomerDTO());
//            model.addAttribute("typeList", typeService.findAll());
//            return "customer/form";
//    }

//    // update or create
//    @PostMapping("/update")
//    public String createTargetCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO,
//                                       BindingResult result, Model model, RedirectAttributes attributes, HttpSession session) {
//
//        Optional<Account> account_exists= accountService.findByUsername(customerDTO.getUsername());
//
////        Optional<AccountBanking> accountBanking_exists= accountBankingService.findByBankAndNumberCard(customerDTO.getBank(), customerDTO.getNumberCard());
//
//        if (result.hasErrors()) {
//            model.addAttribute("typeList", typeService.findAll());
//            return "customer/form";
//        }
//
//        Optional<String> id=Optional.ofNullable(customerDTO.getId());
//
//        if(!id.isPresent()){
//
//            if(account_exists.isPresent()){
//                model.addAttribute("typeList", typeService.findAll());
//                model.addAttribute("username_error","Username already exists!");
//                return "customer/form";
//            }
//
//            Account account=new Account(customerDTO);
//            AccountBanking accountBanking=new AccountBanking(customerDTO);
////            Optional<Role> defaultRole=rolesService.findByName("user");
////            List<Role> roles=new ArrayList<>();
////
////            if(defaultRole.isPresent()){
////                roles.add(defaultRole.get());
////                account.setList(roles);
////            }
//
//
//            accountService.save(account);
//            accountBankingService.save(accountBanking);
//
//            customerService.save(new Customer(customerDTO, account, accountBanking));
//
////            session.setAttribute("username",account.getUsername());
//            session.setAttribute("username",account.getUsername());
//
//            model.addAttribute("message","Create Success !!!");
//            model.addAttribute("customer",new CustomerDTO(customerService.findCustomerByUsername(customerDTO.getUsername()).get()));
//            model.addAttribute("form",new FormSearchDTO());
//            model.addAttribute("customerType",typeService.findAll());
//            model.addAttribute("username",account.getUsername());
//            return "customer/layout";
//        } else {
//
//            Customer customer = new Customer(customerDTO);
//            customer.setId(customerDTO.getId());
//            customerService.save(customer);
//            AccountBanking accountBanking=new AccountBanking(customerDTO);
//            accountBanking.setId(customerDTO.getAccount_bank_id());
//            accountBankingService.save(accountBanking);
//
//            model.addAttribute("customer",customer);
//            model.addAttribute("message","Edit Success !!!");
//        }
//
//        model.addAttribute("form",new FormSearchDTO());
//        model.addAttribute("list",roomTypeService.findAll());
//        model.addAttribute("customerType",typeService.findAll());
//        return "customer/layout";
//    }

    @RequestMapping("/delete")
    public String deleteById(@RequestParam("id") String id, Model model) {
        customerService.updateById(id);
//        model.addAttribute("message", "Delete Success !!!");
//        model.addAttribute("form",new FormSearchDTO());
//        model.addAttribute("list",roomTypeService.findAll());
        return "redirect:/customer";
    }

    @RequestMapping("/MyAccount")
    public String goToMyAccount(Model model, HttpSession session){
//        Optional<Customer> customer=customerService.findCustomerByUsername(username);
//        if(customer.isPresent()){
//
//            model.addAttribute("username",username);
//            return "customer/layout";
//        }
//        return "home";
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=((UserDetails) authentication.getPrincipal()).getUsername();
        Optional<Customer> customer = customerService.findCustomerByUsername(username);
        customer.ifPresent(value -> model.addAttribute("customer", new CustomerDTO(value)));
        model.addAttribute("customerType",typeService.findAll());
        return "customer/layout";
    }

}
