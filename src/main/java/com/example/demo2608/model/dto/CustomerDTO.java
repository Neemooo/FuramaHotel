package com.example.demo2608.model.dto;

import com.example.demo2608.annotation_custom.ValidDayBirthBigger18;
import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.role.Account;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

//  1) Customer:

    String id;

    Integer customer_type;

    @NotBlank(message = "name should be written something")
    String name;

    @NotBlank(message = "birthday should be written something")
    @ValidDayBirthBigger18
    String birthday;

    @NotBlank(message = "id_card should be written something")
    @Pattern(regexp = "^\\d{9}$",message = "id card should be correct type 9 number")
    String id_card;

    @NotNull(message = "Gender should be specified")
    Integer gender;

    @NotBlank(message = "phone should be something")
    @Pattern(regexp = "^(0|84|\\+84)\\d{9}$",message = "phone should be correct type in Viet Nam (0xxxxxxxxx or +84xxxxxxxx or 84xxxxxxxx)")
    String phone;

    @NotBlank(message = "email should be written something")
    @Email(message = "email should be input correct type")
    String email;

    @NotBlank(message = "address should be written something")
    String address;

//  2) Account:

//    @NotBlank(message = "address should be written something")
    String username;

//    @NotBlank(message = "address should be written something")
    String password;

//  3) AccountBanking:

    Long account_bank_id;

//    @NotBlank(message = "name should be written something")
    String bank;

//    @NotBlank(message = "name should be written something")
//    double balance;

//    @NotBlank(message = "name should be written something")
    String expiration_date;

//    dung chung voi name:

//    @NotBlank(message = "name should be written something")
//    String name;

//    @NotBlank(message = "name should be written something")
    @Size(min=10, max=12, message = "numberCard should is from 10 to 12 numbers!")
    String numberCard;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.customer_type = customer.getCustomer_type().getId();
        this.birthday = customer.getBirthday();
        this.id_card = customer.getId_card();
        this.gender = customer.getGender();
        this.phone = customer.getPhone();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.username=customer.getAccount().getUsername();
        this.password=customer.getAccount().getPassword();
        this.account_bank_id=customer.getAccountBanking().getId();
        this.bank=customer.getAccountBanking().getBank();
        this.expiration_date=customer.getAccountBanking().getExpiration_date();
        this.numberCard=customer.getAccountBanking().getNumberCard();
    }
}
