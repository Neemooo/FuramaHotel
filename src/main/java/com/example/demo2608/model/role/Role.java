package com.example.demo2608.model.role;

import com.example.demo2608.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

//    @Enumerated(EnumType.STRING)
//    private Roles role = Roles.ROLE_USER;

    String name;

    @ManyToMany(mappedBy = "list")
    List<Account> list;

}
