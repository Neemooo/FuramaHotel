package com.example.demo2608.service.account;

import com.example.demo2608.model.role.Account;
import com.example.demo2608.service.interface_business.IAccountService;
import com.example.demo2608.service.interface_business.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	IAccountService accountService;

	@Autowired
	IRolesService rolesService;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		Account account = accountService.findAccountByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("Username " + username + " was not found in the database");
		}
//		if (user == null) {
//			throw new UsernameNotFoundException("Could not find user");
//		}
		return new MyUserDetails(account);
	}

}
