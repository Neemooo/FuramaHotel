package com.example.demo2608.repository.service;

import com.example.demo2608.model.reservation.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRepos extends JpaRepository<Service,Integer> {
}
