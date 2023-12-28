package com.example.demo2608.model.employee;

import com.example.demo2608.model.dto.EmployeeDTO;
import com.example.demo2608.model.employee.type.Division;
import com.example.demo2608.model.employee.type.Education;
import com.example.demo2608.model.role.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "person-generator")
    @GenericGenerator(name = "person-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "EM-"),
            strategy = "com.example.demo2608.util.PersonAutoGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "id_card")
    private String id_card;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "gender")
    int gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "division", nullable = false, referencedColumnName = "id")
    private Division division;

    @ManyToOne
    @JoinColumn(name = "education", nullable = false, referencedColumnName = "id")
    private Education education;

    @OneToOne
    @JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
    private Account account;

    public Employee(EmployeeDTO employeeDTO){
        this.id=null;
        this.salary=6000.0;
        Division division=new Division();
        division.setId(employeeDTO.getDivision());

        Education education=new Education();
        education.setId(employeeDTO.getEducation());

        this.name=employeeDTO.getName();
        this.gender=employeeDTO.getGender();
        this.birthday=employeeDTO.getBirthday();
        this.id_card=employeeDTO.getId_card();
        this.phone=employeeDTO.getPhone();
        this.email=employeeDTO.getEmail();
        this.address=employeeDTO.getAddress();
        this.status="on";
        this.division=division;
        this.education=education;

    }

    public Employee(EmployeeDTO employeeDTO, Account account){
        this.id=null;
        this.salary=8000000.0;

        Division division=new Division();
        division.setId(employeeDTO.getDivision());

        Education education=new Education();
        education.setId(employeeDTO.getEducation());

        this.name=employeeDTO.getName();
        this.gender=employeeDTO.getGender();
        this.birthday=employeeDTO.getBirthday();
        this.id_card=employeeDTO.getId_card();
        this.salary=employeeDTO.getSalary();
        this.phone=employeeDTO.getPhone();
        this.email=employeeDTO.getEmail();
        this.address=employeeDTO.getAddress();
        this.status="on";
        this.division=division;
        this.education=education;
        this.account=account;
    }
}
