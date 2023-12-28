package com.example.demo2608.service.customer;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.repository.customer.IAccountBankingRepos;
import com.example.demo2608.service.interface_business.IAccountBankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountBankingService implements IAccountBankingService {

    @Autowired
    IAccountBankingRepos repos;

    @Override
    public List<AccountBanking> findAll() {
        return repos.findAll();
    }

    @Override
    public AccountBanking save(AccountBanking accountBanking) {
        return repos.save(accountBanking);
    }

    @Override
    public Optional<AccountBanking> findById(Long id) {
        return repos.findById(id);
    }

    @Override
    public void deleteById(Long id) {
            repos.deleteById(id);
    }

    @Override
    public Page<AccountBanking> findAll(Pageable pageable) {
        return repos.findAll(pageable);
    }

    @Override
    public Optional<AccountBanking> findByBankAndNumberCard(String bank, String number) {
        return repos.findByBankAndNumberCard(bank, number);
    }

    @Override
    public void updateBalanceById(Long id, Double balance) {
        repos.updateBalanceById(id,balance);
    }


}
