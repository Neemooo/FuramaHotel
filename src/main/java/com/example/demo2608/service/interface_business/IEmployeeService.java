package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.employee.Employee;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IEmployeeService extends IBaseService<Employee> {
    Employee findByStringId(String id);
    void deleteByStringId(String id);
    Page<Employee> findByName(String name, Pageable pageable);

    Optional<Employee> findEmployeeByUsername(String username);

    Page<Employee> findEmployeeByNameContainingOrPhoneOrId_card(String search, Pageable pageable);

    void updateById(@Param("id_delete") String id_delete);

    Account findAccountByEmployeeId(String id);
}
