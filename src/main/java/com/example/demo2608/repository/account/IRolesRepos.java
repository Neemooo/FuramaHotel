package com.example.demo2608.repository.account;

import com.example.demo2608.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 import java.util.Optional;

@Repository
public interface IRolesRepos extends JpaRepository<Role, Integer> {

    Optional<Role>findByName(String name);
}
