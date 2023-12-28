package com.example.demo2608.model.customer;

import com.example.demo2608.model.dto.AccountBankingDTO;
import com.example.demo2608.model.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "account_banking")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountBanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "bank")
    String bank;

    @Column(name = "balance")
    Double balance;

    @Column(name = "expiration_date")
    String expiration_date;

    @Column(name = "name")
    String name;

    @Column(name = "number_card")
    String numberCard;

    public AccountBanking(AccountBankingDTO accountBankingDTO){
        this.id=null;
        this.bank=accountBankingDTO.getBank();
        this.balance=9999999.0;
        this.expiration_date=accountBankingDTO.getExpiration_date();
        this.name=accountBankingDTO.getName();
        this.numberCard=accountBankingDTO.getNumberCard();
    }

    public AccountBanking(CustomerDTO customerDTO){
        this.id=null;
        this.balance=9999999.0;
        this.bank=customerDTO.getBank();
        this.expiration_date=customerDTO.getExpiration_date();
        this.name=customerDTO.getName();
        this.numberCard=customerDTO.getNumberCard();
    }
}
