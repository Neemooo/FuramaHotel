package com.example.demo2608.model.role;

import com.example.demo2608.model.dto.CustomerDTO;
import com.example.demo2608.model.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @Column(columnDefinition = "varchar(255)")
    String username;

    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> list;

    public Account(CustomerDTO customerDTO){
        String salt = BCrypt.gensalt();
        this.username = customerDTO.getUsername();
        this.password = BCrypt.hashpw(customerDTO.getPassword(), salt);
    }

    public Account(EmployeeDTO employeeDTO){
        String salt = BCrypt.gensalt();
        this.username = employeeDTO.getUsername();
        this.password = BCrypt.hashpw(employeeDTO.getPassword(), salt);
    }

}
