package com.example.demo2608.service.account;


import com.example.demo2608.model.role.Account;
import com.example.demo2608.model.role.Role;
import com.example.demo2608.repository.account.IAccountRepos;
import com.example.demo2608.service.interface_business.IAccountService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements IAccountService {

    @Autowired
    IAccountRepos repos;

    @Override
    public List<Account> findAll() {
       return repos.findAll();
    }

    @Override
    public Account save(Account account) {
        return repos.save(account);
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        return repos.findByUsername(username);
    }

    @SneakyThrows
    @Override
    public List<Role> findAllRoleByUsername(String username) {
        Optional<Account> account =  repos.findByUsername(username) ;
        if (account.isPresent()) {
            return account.get().getList();
        }
        throw new Exception(String.format("Not found account when username is %s", username));
    }

    @SneakyThrows
    @Override
    public Account findAccountByUsername(String username) {
        Optional<Account> account = repos.findByUsername(username);
        if(account.isPresent()){
            return account.get() ;
        }
        throw new Exception("Error");

    }

    @Override
    public void deleteByUsername(String username) {
        repos.deleteByUsername(username);
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return repos.findAll(pageable);
    }

//    @Override
    public Optional<Account> findAccountByUsernameAndPass(String username, String password) {
        return repos.findAccountByUsernameAndPassword(username, password);
    }

}
