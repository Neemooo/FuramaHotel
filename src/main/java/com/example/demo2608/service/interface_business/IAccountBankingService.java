package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.service.IBaseService;

import java.util.Optional;

public interface IAccountBankingService extends IBaseService<AccountBanking> {
    Optional<AccountBanking> findByBankAndNumberCard(String bank, String number);
    void updateBalanceById(Long id, Double balance);
}
