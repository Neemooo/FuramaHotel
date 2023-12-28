package com.example.demo2608.service.booking;

import com.example.demo2608.model.reservation.booking.Transaction_history;
import com.example.demo2608.repository.booking.ITransactionRepos;
import com.example.demo2608.service.interface_business.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    ITransactionRepos repos;

    @Override
    public List<Transaction_history> findAll() {
        return repos.findAll();
    }

    @Override
    public Transaction_history save(Transaction_history transaction_history) {
        return repos.save(transaction_history);
    }

    @Override
    public Optional<Transaction_history> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Page<Transaction_history> findAll(Pageable pageable) {
        return repos.findAll(pageable);
    }
}
