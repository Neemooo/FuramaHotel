package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.role.Account;
import com.example.demo2608.model.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<Account> findAll();

    Account save(Account account);

    Optional<Account> findByUsername(String username);
    List<Role> findAllRoleByUsername(String username);
    Account findAccountByUsername(String username);

    void deleteByUsername(String username);

    Page<Account> findAll(Pageable pageable);

//    Optional<Account> findAccountByUsernameAndPass(String username, String password);

}

