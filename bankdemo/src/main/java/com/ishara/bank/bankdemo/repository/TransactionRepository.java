package com.ishara.bank.bankdemo.repository;

import com.ishara.bank.bankdemo.entity.Account;
import com.ishara.bank.bankdemo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByAccountOrderByTimestampDesc(Account account);
}
