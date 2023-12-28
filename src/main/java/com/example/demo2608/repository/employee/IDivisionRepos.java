package com.example.demo2608.repository.employee;

import com.example.demo2608.model.employee.type.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDivisionRepos extends JpaRepository<Division, Integer> {
}
