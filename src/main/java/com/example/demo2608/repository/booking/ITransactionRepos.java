package com.example.demo2608.repository.booking;

import com.example.demo2608.model.reservation.booking.Discount;
import com.example.demo2608.model.reservation.booking.Transaction_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepos extends JpaRepository<Transaction_history, Integer> {
}
