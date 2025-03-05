package com.ishara.bank.bankdemo.entity;

import com.ishara.bank.bankdemo.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor

public class Account{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    @Enumerated(EnumType.ORDINAL)
    private AccountType type;
    private String status;

    public Account(String accountNumber, BigDecimal balance, AccountType type, String status, Customer customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.status = status;
//        this.customerId = customerId;
    }

//    @ManyToOne
//    private Customer customerId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountType getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

//    public Customer getCustomerId() {
//        return customerId;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public void setCustomerId(Customer customerId) {
//        this.customerId = customerId;
//    }
}