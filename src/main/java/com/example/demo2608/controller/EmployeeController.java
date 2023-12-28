package com.example.demo2608.controller;

import com.example.demo2608.model.dto.EmployeeDTO;
import com.example.demo2608.model.dto.FormSearchDTO;
import com.example.demo2608.model.employee.Employee;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.model.role.Role;
import com.example.demo2608.service.IMailSender;
import com.example.demo2608.service.account.AccountService;
import com.example.demo2608.service.account.RolesService;
import com.example.demo2608.service.employee.DivisionService;
import com.example.demo2608.service.employee.EducationService;
import com.example.demo2608.service.employee.EmployeeService;
import com.example.demo2608.service.rooms.RoomTypeService;
import lombok.SneakyThrows;
import net.bytebuddy.description.NamedElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    final int MAX_DISPLAY = 10;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EducationService educationService;

    @Autowired
    DivisionService divisionService;

    @Autowired
    AccountService accountService;

    @Autowired
    RolesService rolesService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    IMailSender mailSender;

    private AuthenticationManager authenticationManager;

//    public RegistrationService(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

    @RequestMapping("")
    public String findByName(Model model, @RequestParam(defaultValue = "") String name_search,
                             @PageableDefault(size = MAX_DISPLAY, sort = "name", direction = Sort.Direction.ASC)
                                     Pageable pageable) {

        Page<Employee> employeeList = employeeService.findEmployeeByNameContainingOrPhoneOrId_card(name_search, pageable);

        model.addAttribute("list", employeeList);
        model.addAttribute("name_search", name_search);
        return "employee/index";
    }

    @RequestMapping("/delete")
    public String deleteById(@RequestParam("id") String id, RedirectAttributes attributes) {
        employeeService.updateById(id);
//        model.addAttribute("form",new FormSearchDTO());
//        model.addAttribute("list",roomTypeService.findAll());
        return "redirect:/employee";
    }

    @SneakyThrows
    @RequestMapping("/create")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        transferDataAddOnEmployee(model);
        return "employee/form";
    }

    @RequestMapping("/edit/{id}")
    public String editEmployee(@PathVariable String id, Model model) {
        Optional<Employee> employee = Optional.ofNullable(employeeService.findByStringId(id));
        if (employee.isPresent()) {
            model.addAttribute("employee", new EmployeeDTO(employee.get()));
            transferDataAddOnEmployee(model);
            return "employee/form";
        }

        return "redirect:/";
    }

    @RequestMapping("/MyAccount")
    public String goToMyAccount(HttpSession session, Model model) {
//        Optional<Employee> employee = employeeService.findEmployeeByUsername((String) session.getAttribute("username"));
//        employee.ifPresent(value -> model.addAttribute("employee", new EmployeeDTO(value)));
//        transferDataAddOnEmployee(model);
//        return "employee/layout";

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=((UserDetails) authentication.getPrincipal()).getUsername();
        Optional<Employee> employee = employeeService.findEmployeeByUsername(username);
        employee.ifPresent(value -> model.addAttribute("employee", new EmployeeDTO(value)));
        transferDataAddOnEmployee(model);
        return "employee/layout";
    }

    @PostMapping("/update")
    @Transactional(rollbackFor = Exception.class)
//    @Transactional(rollbackOn = Exception.class)
    public String createTargetEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO,
                                       BindingResult result, Model model, RedirectAttributes attributes,
                                       HttpSession session) {
//        try {
            if (result.hasErrors()) {
                transferDataAddOnEmployee(model);
                return "employee/form";
            }

            Optional<String> id = Optional.ofNullable(employeeDTO.getId());

            if (!id.isPresent() || id.get().isEmpty()) {

                if(!inspectUsernameAndPassIsPresent(employeeDTO,model)){
                    transferDataAddOnEmployee(model);
                    return "employee/form";
                }

// create new employee:
                Account account = new Account(employeeDTO);

//                Optional<Role> defaultRole = rolesService.findByName("admin");
//                List<Role> roles = new ArrayList<>();
//
//                if (defaultRole.isPresent()) {
//                    roles.add(defaultRole.get());
//                    account.setList(roles);
//                }
                accountService.save(account);
                Optional<Account> accountOptional = accountService.findByUsername(employeeDTO.getUsername());
                employeeService.saveEmployeeWithAccount(new Employee(employeeDTO), accountOptional.get());
                mailSender.sendMessageWithAttachment(employeeDTO.getEmail(),"Hello "+employeeDTO.getName(),"<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "  <title>Welcome to Our Company!</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <p>We are thrilled to welcome you to our team at Furama company. This is an exciting opportunity for you to join us on our journey!</p>\n" +
                        "  <p>Our work environment is designed to facilitate your personal growth and contribute to our mission and goals.</p>\n" +
                        "  <p>If you need any assistance or information, please don't hesitate to contact us through this email address.</p>\n" +
                        "  <p>Thank you and have a wonderful day!</p>\n" +
                        "  <p>Best regards,</p>\n" +
                        "<a href=\"http://localhost:8080/\">Visit our website</a>\n" +
                        "</body>\n" +
                        "</html>", "http://localhost:8080/");
                return "redirect:/employee";
            } else {
//            update employee
                Employee employee=employeeService.findByStringId(employeeDTO.getId());
                Account account = employee.getAccount();
                employee=new Employee(employeeDTO,account);
                employee.setId(employeeDTO.getId());
                employeeService.save(employee);
            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }

        return "redirect:/employee";
    }

    private void transferDataAddOnEmployee(Model model) {
        model.addAttribute("eduList", educationService.findAll());
        model.addAttribute("divList", divisionService.findAll());
    }

    private boolean inspectUsernameAndPassIsPresent(EmployeeDTO employeeDTO, Model model){
        Optional<String> username = Optional.ofNullable(employeeDTO.getUsername());
        Optional<String> password= Optional.ofNullable(employeeDTO.getPassword());

        if(!username.isPresent() || username.get().isEmpty()){
            model.addAttribute("username_error","Username should be something");
            return false;
        }

        Optional<Account> account_exists = accountService.findByUsername(employeeDTO.getUsername());
        if (account_exists.isPresent()) {
                model.addAttribute("username_error", "Username already exists!");
                return false;
        }

        if(!password.isPresent() || password.get().isEmpty()){
            model.addAttribute("password_error","Password should be something");
            return false;
        }

        return true;
    }

}
