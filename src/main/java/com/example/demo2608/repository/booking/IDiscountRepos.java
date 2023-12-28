package com.example.demo2608.repository.booking;

import com.example.demo2608.model.reservation.booking.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IDiscountRepos extends JpaRepository<Discount, Integer> {

//    @Query("select Discount from Discount d where Discount.id=?1 and Discount.beginning_date <= ?2 and Discount.expiration_date>=?2")
//    Optional<Discount> findValidDiscountByCode(String code, String date);
@Query("select d from Discount d where d.id = ?1 and d.beginning_date <= ?2 and d.expiration_date >= ?2")
Optional<Discount> findValidDiscountByCode(String id, String date);

}
