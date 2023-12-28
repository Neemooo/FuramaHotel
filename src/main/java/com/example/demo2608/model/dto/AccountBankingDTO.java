package com.example.demo2608.model.dto;

import com.example.demo2608.model.customer.AccountBanking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountBankingDTO {

    Long id;

    @NotBlank(message = "name should be written something")
    String bank;

    @NotBlank(message = "name should be written something")
    double balance;

    @NotBlank(message = "name should be written something")
    String expiration_date;

    @NotBlank(message = "name should be written something")
    String name;

    @NotBlank(message = "name should be written something")
    String numberCard;

    public AccountBankingDTO(AccountBanking accountBanking){
        this.id=accountBanking.getId();
        this.bank=accountBanking.getBank();
        this.balance=accountBanking.getBalance();
        this.expiration_date=accountBanking.getExpiration_date();
        this.name=accountBanking.getName();
        this.numberCard=accountBanking.getNumberCard();
    }
}
