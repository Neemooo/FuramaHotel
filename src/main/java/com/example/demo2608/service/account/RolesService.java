package com.example.demo2608.service.account;

import com.example.demo2608.model.role.Role;
import com.example.demo2608.repository.account.IRolesRepos;
import com.example.demo2608.service.interface_business.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RolesService implements IRolesService {

    @Autowired
    IRolesRepos repos;

    @Override
    public List<Role> findAll() {
        return repos.findAll();
    }

    public Optional<Role> findById(Integer id){
        return repos.findById(id);
    }

    public Optional<Role> findByName(String name){
        return repos.findByName(name);
    }


}
