package com.example.demo2608.repository.customer;

import com.example.demo2608.model.customer.AccountBanking;
import com.example.demo2608.model.role.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IAccountBankingRepos extends JpaRepository<AccountBanking, Long> {

    Optional<AccountBanking> findByBankAndNumberCard(String bank, String number);

    @Modifying
    @Transactional
    @Query(value = "update Account_banking a set a.balance = :balance where a.id = :id", nativeQuery = true)
    void updateBalanceById(Long id, Double balance);
}
