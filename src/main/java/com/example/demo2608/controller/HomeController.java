package com.example.demo2608.controller;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.customer.Customer_type;
import com.example.demo2608.model.dto.CustomerDTO;
import com.example.demo2608.model.dto.EmployeeDTO;
import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.employee.Employee;
import com.example.demo2608.model.reservation.room.Room_type;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.model.role.Role;
import com.example.demo2608.service.IMailSender;
import com.example.demo2608.service.ITypeService;
import com.example.demo2608.service.account.RolesService;
import com.example.demo2608.service.customer.CustomerService;
import com.example.demo2608.service.interface_business.IAccountBankingService;
import com.example.demo2608.service.interface_business.IAccountService;
import com.example.demo2608.service.rooms.RoomTypeService;
import com.example.demo2608.util.SearchError;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequestMapping("")
public class HomeController {

    final int MAX_DISPLAY = 10;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    IMailSender mailSender;

    @Autowired
    IAccountService accountService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ITypeService<Customer_type> typeService;

    @Autowired
    IAccountBankingService accountBankingService;

    @Autowired
    RolesService rolesService;

//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    public void RegistrationService(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @GetMapping({"","/Myfurama"})
    public String goHomeRedirect(Model model, HttpSession session){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
    String username=((UserDetails) authentication.getPrincipal()).getUsername();


//    mailSender.sendMessageWithAttachment("minhtri241096@gmail.com","Hello ","<!DOCTYPE html>\n" +
//            "<html>\n" +
//            "<head>\n" +
//            "  <title>Welcome to Our Website!</title>\n" +
//            "</head>\n" +
//            "<body>\n" +
//            "  <h1>Welcome to Our New Website!</h1>\n" +
//            "  <p>Thank you for creating a new account.</p>\n" +
//            "  <p>Now you can experience the fantastic features of our website.</p>\n" +
//            "  <p>If you need any assistance, feel free to contact us.</p>\n" +
//            "  <a href=\"http://localhost:8080/\">Visit our website</a>\n" +
//            "  <p>Thank you!</p>\n" +
//            "</body>\n" +
//            "</html>", "http://localhost:8080/");

    model.addAttribute("username",session.getAttribute("username"));
        }

        model.addAttribute("form",new FormSearchDTO());
        model.addAttribute("list",roomTypeService.findAll());
        return "home";
//        return goHome(model, session);
    }

    @GetMapping("/home")
    public String goHome(Model model, HttpSession session){
        model.addAttribute("form",new FormSearchDTO());
        model.addAttribute("list",roomTypeService.findAll());
        return "home";
    }

    @PostMapping("/search")
    public String searchHome(@Valid @ModelAttribute("form") FormSearchDTO formSearchDTO, BindingResult result, Model model, HttpSession session, Authentication authentication){

//       to inspect errors:
        if(result.hasErrors()) {
            model.addAttribute("list",roomTypeService.findAll());
           return "home";
        }

       if(SearchError.hasErrorPeopleLimit(formSearchDTO)>4){
           model.addAttribute("totalPeopleLimit","Total number of people exceeds the limit");
           return goHome(model, session);
       }

//       list of rooms are not reserved from checkin to checkout:

        List<Room_type> room_types= roomTypeService.roomsAreNotReserved(formSearchDTO.getCheckin(), formSearchDTO.getCheckout());

//      inspect list of rooms have condition (max_people):
        room_types=roomTypeService.findRoomsByPeople(room_types,SearchError.hasErrorPeopleLimit(formSearchDTO));

       model.addAttribute("list", room_types);

        session.setAttribute("form",formSearchDTO);

        session.setAttribute("check_in",formSearchDTO.getCheckin());

        session.setAttribute("check_out",formSearchDTO.getCheckout());
        return "roomType/index";
    }

    @RequestMapping("/create")
    public String createCustomer(Model model, HttpSession session) {
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("typeList", typeService.findAll());
        return "customer/form";
    }

    @PostMapping("/update")
    public String createTargetCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO,
                                       BindingResult result, Model model) {



//        Optional<AccountBanking> accountBanking_exists= accountBankingService.findByBankAndNumberCard(customerDTO.getBank(), customerDTO.getNumberCard());

        if (result.hasErrors()) {
            model.addAttribute("typeList", typeService.findAll());
            return "customer/form";
        }

        Optional<String> id=Optional.ofNullable(customerDTO.getId());

        if(!id.isPresent() || id.get().isEmpty()){

            if(!inspectUsernameAndPassIsPresent(customerDTO, model)){
                model.addAttribute("typeList", typeService.findAll());
                return "customer/form";
            }

//            Optional<Account> account_exists= accountService.findByUsername(customerDTO.getUsername());
//
//            if(account_exists.isPresent()){
//                model.addAttribute("typeList", typeService.findAll());
//                model.addAttribute("username_error","Username already exists!");
//                return "customer/form";
//            }

            Account account=new Account(customerDTO);
            AccountBanking accountBanking=new AccountBanking(customerDTO);
//            Optional<Role> defaultRole=rolesService.findByName("user");
//            List<Role> roles=new ArrayList<>();
//
//            if(defaultRole.isPresent()){
//                roles.add(defaultRole.get());
//                account.setList(roles);
//            }


            accountService.save(account);

            accountBankingService.save(accountBanking);

            Optional<Account> accountOptional= accountService.findByUsername(customerDTO.getUsername());

            Optional<AccountBanking> accountBankingOptional=accountBankingService.findByBankAndNumberCard(accountBanking.getBank(),accountBanking.getNumberCard());

            customerService.save(new Customer(customerDTO, accountOptional.get(), accountBankingOptional.get()));
//            session.setAttribute("username",account.getUsername());

//            Authentication authentication = new UsernamePasswordAuthenticationToken(customerDTO.getUsername(), customerDTO.getPassword());
//
//        // Thực hiện xác thực
//        Authentication authenticated = authenticationManager.authenticate(authentication);
//
//        // Nếu xác thực thành công, đặt Authentication vào SecurityContextHolder
//        if (authenticated != null && authenticated.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(authenticated);
//        }

//            model.addAttribute("message","Create Success !!!");
//            model.addAttribute("customer",new CustomerDTO(customerService.findCustomerByUsername(customerDTO.getUsername()).get()));
//            model.addAttribute("form",new FormSearchDTO());
//            model.addAttribute("customerType",typeService.findAll());
//            model.addAttribute("username",account.getUsername());

            mailSender.sendMessageWithAttachment(customerDTO.getEmail(),"Hello "+customerDTO.getName(),"<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "  <title>Welcome to Our Website!</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "  <h1>Welcome to Our New Website!</h1>\n" +
                    "  <p>Thank you for creating a new account.</p>\n" +
                    "  <p>Now you can experience the fantastic features of our website.</p>\n" +
                    "  <p>If you need any assistance, feel free to contact us.</p>\n" +
                    "<a href=\"http://localhost:8080/\">Visit our website</a>\n" +
                    "  <p>Thank you!</p>\n" +
                    "</body>\n" +
                    "</html>", "http://localhost:8080/");
            return "redirect:/login";
        } else {

            Customer customer=customerService.findByStringId(customerDTO.getId());
            Account account=customer.getAccount();
            AccountBanking accountBanking=customer.getAccountBanking();

            customer = new Customer(customerDTO,account,accountBanking);
            customer.setId(customerDTO.getId());
//            customer.setAccount(account);
//            customer.setAccountBanking(accountBanking);
            customerService.save(customer);
        }

        return "redirect:/customer";
    }

    private boolean inspectUsernameAndPassIsPresent(CustomerDTO customerDTO, Model model){
        Optional<String> username = Optional.ofNullable(customerDTO.getUsername());
        Optional<String> password= Optional.ofNullable(customerDTO.getPassword());
        Optional<String> bank= Optional.ofNullable(customerDTO.getBank());
        Optional<String> expiration_date=Optional.ofNullable(customerDTO.getExpiration_date());
        Optional<String> numberCard= Optional.ofNullable(customerDTO.getNumberCard());

        if(!username.isPresent() || username.get().isEmpty()){
            model.addAttribute("username_error","Username should be something");
            return false;
        }

        if(!password.isPresent() || password.get().isEmpty()){
            model.addAttribute("password_error","Password should be something");
            return false;
        }

        if(!bank.isPresent() || bank.get().isEmpty()){
            model.addAttribute("bank_error","Bank should be something");
            return false;
        }

        if(!expiration_date.isPresent() || expiration_date.get().isEmpty()){
            model.addAttribute("expiration_error","Expiration_date should be something");
            return false;
        }

        if(!numberCard.isPresent() || numberCard.get().isEmpty()){
            model.addAttribute("numberCard_error","NumberCard should be something");
            return false;
        }

        Optional<Account> account_exists = accountService.findByUsername(customerDTO.getUsername());
        if (account_exists.isPresent()) {
            model.addAttribute("username_error", "Username already exists!");
            return false;
        }

        return true;
    }

}
