package com.example.demo2608.model.customer;

import com.example.demo2608.model.dto.CustomerDTO;
import com.example.demo2608.model.role.Account;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

//@Entity
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "person-generator")
    @GenericGenerator(name = "person-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "KH-"),
            strategy = "com.example.demo2608.util.PersonAutoGenerator")
    private String id;

    @OneToOne
    @JoinColumn(name = "customer_type", nullable = false, referencedColumnName = "id")
    private Customer_type customer_type;

    @OneToOne
    @JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
    private Account account;

    @OneToOne
    @JoinColumn(name = "account_banking", nullable = false, referencedColumnName = "id")
    private AccountBanking accountBanking;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    int gender;

    @Column(name = "id_card")
    private String id_card;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    public Customer(CustomerDTO customerDTO, Account account, AccountBanking accountBanking) {

        Customer_type customerType = new Customer_type();

        if(customerDTO.getCustomer_type()==null){
            customerType.setId(1);
        } else {
            customerType.setId(customerDTO.getCustomer_type());
        }

        this.id = null;
        this.name = customerDTO.getName();
        this.birthday = customerDTO.getBirthday();
        this.customer_type = customerType;
        this.id_card = customerDTO.getId_card();
        this.gender = customerDTO.getGender();
        this.phone = customerDTO.getPhone();
        this.email = customerDTO.getEmail();
        this.address = customerDTO.getAddress();
        this.account=account;
        this.accountBanking=accountBanking;
        this.status="on";
    }


    public Customer(CustomerDTO customerDTO) {

        Customer_type customerType = new Customer_type();
        customerType.setId(customerDTO.getCustomer_type());

        this.id = null;
        this.name = customerDTO.getName();
        this.birthday = customerDTO.getBirthday();
        this.customer_type = customerType;
        this.id_card = customerDTO.getId_card();
        this.gender = customerDTO.getGender();
        this.phone = customerDTO.getPhone();
        this.email = customerDTO.getEmail();
        this.address = customerDTO.getAddress();
        this.status="on";
    }
}
