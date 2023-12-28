package com.example.demo2608.service.employee;

import com.example.demo2608.model.employee.type.Education;
import com.example.demo2608.repository.employee.IEducationRepos;
import com.example.demo2608.service.interface_business.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService implements IEducationService {
    @Autowired
    IEducationRepos repos;

    @Override
    public List<Education> findAll() {
        return repos.findAll();
    }
}
