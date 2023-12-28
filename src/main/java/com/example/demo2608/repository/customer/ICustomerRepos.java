package com.example.demo2608.repository.customer;

import com.example.demo2608.model.customer.Customer;
import com.example.demo2608.model.role.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICustomerRepos extends JpaRepository<Customer, Long> {
    Customer findById(String id);
    void deleteById(String id);
    Page<Customer> findByNameContaining(String name, Pageable pages);
    Optional<Customer> findByAccount_Username(String username);

    @Query(value = "select * from customer c where (name like concat('%',:search,'%') or email like concat('%',:search,'%')  or phone = :search or id_card = :search) and status='on'", nativeQuery = true)
    Page<Customer> findCustomerBySearch(String search, Pageable pages);

    @Modifying
    @Transactional
    @Query(value = "update Customer c set c.status = 'off' where c.id = :id_delete", nativeQuery = true)
    void updateById(@Param("id_delete") String id_delete);

    @Query("SELECT c.account FROM Customer c WHERE c.id = :id")
    Account findAccountByCustomerId(@Param("id") String id);

}

