package com.ishara.bank.bankdemo.entity;

import com.ishara.bank.bankdemo.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Transaction {
    private static long counter = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private TransactionType type;
    @ManyToOne
    private Account account;
    private LocalDateTime timestamp;

    public Transaction(Account account, BigDecimal amount, TransactionType credit) {
        this.account = account;
        this.amount =amount;
        this.type = credit;
        LocalDate currentDate = LocalDate.now();

        // Define the desired format (yyyyMMdd)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Format the current date into the desired string format
        String formattedDate = currentDate.format(formatter);
        this.id=generateRequestId(formattedDate);
        this.timestamp=LocalDateTime.now();;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public static synchronized Long generateRequestId(String date) {
        return counter < 10 ? Long.parseLong(date + "0" + counter++) : Long.parseLong(date + counter++);
    }
}
