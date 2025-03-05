package com.ishara.bank.bankdemo.dto;

import com.ishara.bank.bankdemo.AccountType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceResponse {
    private String accountNumber;
    private AccountType accountType;
    private String accountName;
    private String currency;

    private BigDecimal currentBalance;
    private BigDecimal availableBalance;

    // Private constructor to prevent direct instantiation
    private BalanceResponse(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.accountType = builder.accountType;
        this.accountName = builder.accountName;
        this.currency = builder.currency;
        this.currentBalance = builder.currentBalance;
        this.availableBalance = builder.availableBalance;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    // Static Builder class
    public static class Builder {
        private String accountNumber;
        private AccountType accountType;
        private String accountName;
        private String currency;
        private BigDecimal currentBalance;
        private BigDecimal availableBalance;

        // Builder setter methods
        public Builder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setAccountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder setAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setCurrentBalance(BigDecimal currentBalance) {
            this.currentBalance = currentBalance;
            return this;
        }

        public Builder setAvailableBalance(BigDecimal availableBalance) {
            this.availableBalance = availableBalance;
            return this;
        }

        // Build method to return the final object
        public BalanceResponse build() {
            return new BalanceResponse(this);
        }
    }


}
