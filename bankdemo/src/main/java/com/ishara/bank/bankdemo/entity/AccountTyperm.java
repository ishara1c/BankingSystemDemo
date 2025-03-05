package com.ishara.bank.bankdemo.entity;

import java.math.BigDecimal;
//todo remove class

public enum AccountTyperm {

    SAVINGS("Savings Account", "A standard savings account", new BigDecimal("1000.00"), "2.5%", new BigDecimal("5.00")),
    CHECKING("Checking Account", "A basic checking account", new BigDecimal("500.00"), "1.0%", new BigDecimal("10.00")),
    CREDIT("Credit Account", "A credit-based account", new BigDecimal("0.00"), "10.0%", new BigDecimal("20.00"));

    private String name;
    private String description;
    private BigDecimal minBalance;
    private String interestRate;
    private BigDecimal charges;

    // Constructor for the enum constants
    AccountTyperm(String name, String description, BigDecimal minBalance, String interestRate, BigDecimal charges) {
        this.name = name;
        this.description = description;
        this.minBalance = minBalance;
        this.interestRate = interestRate;
        this.charges = charges;
    }

    // Getter methods for the properties
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getMinBalance() {
        return minBalance;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public BigDecimal getCharges() {
        return charges;
    }

    // Optional: Override toString to customize how enum is displayed
    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
}
