package com.example.demo2608.model.dto;

import com.example.demo2608.annotation_custom.ValidDayBirthBigger18;
import com.example.demo2608.model.employee.Employee;
import com.example.demo2608.model.role.Account;
import lombok.*;

import javax.validation.constraints.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    String id;

    @NotBlank(message = "name should be something")
    String name;

    @NotNull(message = "Gender should be specified")
    Integer gender;

    @NotBlank(message = "birthday should be something")
    @ValidDayBirthBigger18
    String birthday;

    @NotBlank(message = "id_card should be something")
    @Pattern(regexp = "^\\d{9}$", message = "id card should be correct type 9 number")
    String id_card;

    @Min(0)
    Double salary;

    @NotBlank(message = "email should be something")
    @Pattern(regexp = "^(0|84|\\+84)\\d{9}$", message = "phone should be correct type in Viet Nam (0xxxxxxxxx or +84xxxxxxxx or 84xxxxxxxx)")
    String phone;

    @NotBlank(message = "email should be something")
    @Email(message = "email should be input correct type")
    String email;

    @NotBlank(message = "address should be something")
    String address;

    Integer education;
    Integer division;


    //  2) Account:

//    @NotBlank(message = "address should be written something")
    String username;

//    @NotBlank(message = "address should be written something")
    String password;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.gender=employee.getGender();
        this.birthday = employee.getBirthday();
        this.id_card = employee.getId_card();
        this.salary = employee.getSalary();
        this.phone = employee.getPhone();
        this.email = employee.getEmail();
        this.address = employee.getAddress();
        this.education = employee.getEducation().getId();
        this.division = employee.getDivision().getId();
        this.username=employee.getAccount().getUsername();
        this.password=employee.getAccount().getPassword();
    }

}
