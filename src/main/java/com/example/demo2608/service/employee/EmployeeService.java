package com.example.demo2608.service.employee;

import com.example.demo2608.model.employee.Employee;
import com.example.demo2608.model.role.Account;
import com.example.demo2608.repository.account.IAccountRepos;
import com.example.demo2608.repository.employee.IEmployeeRepos;
import com.example.demo2608.service.interface_business.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    IEmployeeRepos repos;

    @Autowired
    IAccountRepos accountRepos;

    @Override
    public List<Employee> findAll() {
        return repos.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return repos.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
            repos.deleteById(id);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return repos.findAll(pageable);
    }


    @Override
    public Employee findByStringId(String id) {
        return  repos.findById(id);
    }

    @Override
    public void deleteByStringId(String id) {
        repos.deleteById(id);
    }

    @Override
    public Page<Employee> findByName(String name, Pageable pageable) {
        return repos.findByNameContaining(name, pageable);
    }

    @Override
    public Optional<Employee> findEmployeeByUsername(String username) {
        return repos.findByAccount_Username(username);
    }

    @Override
    public Page<Employee> findEmployeeByNameContainingOrPhoneOrId_card(String search, Pageable pageable) {
        return repos.findEmployeeBySearch(search, pageable);
    }

    @Override
    public void updateById(String id_delete) {
        repos.updateById(id_delete);
    }

    @Override
    public  Account findAccountByEmployeeId(String id) {
        return repos.findAccountByEmployeeId(id);
    }

    public void saveEmployeeWithAccount(Employee employee, Account account) {
        accountRepos.save(account); // Lưu Account trước
        employee.setAccount(account); // Gán Account vào Employee
        repos.save(employee); // Lưu Employee
    }

}
