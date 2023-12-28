package com.example.demo2608.service.booking;

import com.example.demo2608.model.reservation.booking.Discount;
import com.example.demo2608.repository.booking.IDiscountRepos;
import com.example.demo2608.service.interface_business.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService implements IDiscountService {

    @Autowired
    IDiscountRepos repos;

    @Override
    public Optional<Discount> findValidDiscountByCode(String id, String date) {
        return repos.findValidDiscountByCode(id, date);
    }

    @Override
    public List<Discount> findAll() {
        return repos.findAll();
    }
}
