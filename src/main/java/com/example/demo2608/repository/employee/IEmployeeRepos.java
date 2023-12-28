package com.example.demo2608.repository.employee;

import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.employee.Employee;
import com.example.demo2608.model.role.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IEmployeeRepos extends JpaRepository<Employee, Long> {
    Employee findById(String id);
    void deleteById(String id);
    Page<Employee> findByNameContaining(String name, Pageable pages);

    Optional<Employee> findByAccount_Username(String username);

    @Query(value = "select * from employee c where (name like concat('%',:search,'%') or email like concat('%',:search,'%')  or phone = :search or id_card = :search) and status = 'on' ", nativeQuery = true)
    Page<Employee> findEmployeeBySearch(String search, Pageable pages);

    @Modifying
    @Transactional
    @Query(value = "update Employee e set e.status = 'off' where e.id = :id_delete", nativeQuery = true)
    void updateById(@Param("id_delete") String id_delete);


    @Query("SELECT e.account FROM Employee e WHERE e.id = :id")
    Account findAccountByEmployeeId(@Param("id") String id);
}
