package com.example.demo2608.service.account;

import com.example.demo2608.model.role.Account;
import com.example.demo2608.model.role.Role;
import com.example.demo2608.service.interface_business.IAccountService;
import com.example.demo2608.service.interface_business.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRolesService rolesService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("Username " + username + " was not found in the database");
        }

//        List<Role> roleList = accountService.findAllRoleByUsername(username);
//
//        Collection<GrantedAuthority> list = new ArrayList<>();
//        Collection<GrantedAuthority> list = new ArrayList<>();
//        for (Role role: roleList) {
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//            list.add(grantedAuthority);
//        }
//        List<Role> list=accountService.findAllRoleByUsername(username);
        Collection<GrantedAuthority> list = accountService
                .findAllRoleByUsername(username)
                .stream()
                .map(item -> new SimpleGrantedAuthority(item.getName()))
                .collect(Collectors.toList());

        return new User(account.getUsername(), account.getPassword(), list);
    }
}
