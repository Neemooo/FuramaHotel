package com.example.demo2608.service.interface_business;

import com.example.demo2608.model.reservation.booking.Discount;
import com.example.demo2608.service.ITypeService;

import java.util.Optional;

public interface IDiscountService extends ITypeService<Discount> {
    Optional<Discount> findValidDiscountByCode(String id, String date);
}
