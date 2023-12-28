package com.example.demo2608.repository.account;

import com.example.demo2608.model.role.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepos extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    void deleteByUsername(String username);

    Optional<Account> findAccountByUsernameAndPassword(String username, String password);



}
