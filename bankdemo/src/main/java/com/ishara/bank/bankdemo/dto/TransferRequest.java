package com.ishara.bank.bankdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ishara.bank.bankdemo.AccountType;
import com.ishara.bank.bankdemo.TransactionType;
import io.micrometer.common.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;

public class TransferRequest {

    private String requestId;
    private AccountType accountType;
    private TransactionType type;
    private BigDecimal amount;
    private String fromAccountNumber;
    private String toAccountNumber;
    private String description;
    private Map<String, Object> metadata;

    // Private constructor to prevent direct instantiation
    private TransferRequest(Builder builder) {
        this.requestId = builder.requestId;
        this.type = builder.type;
        this.accountType = builder.accountType;
        this.amount = builder.amount;
        this.fromAccountNumber = builder.fromAccountNumber;
        this.toAccountNumber = builder.toAccountNumber;
        this.description = builder.description;
        this.metadata = builder.metadata;
    }

    // Static Builder class
    public static class Builder {
        private String requestId;
        private TransactionType type;
        private BigDecimal amount;
        private AccountType accountType;
        private String fromAccountNumber;
        private String toAccountNumber;
        private String description;
        private Map<String, Object> metadata;

        // Builder setter methods
        public Builder setRequestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder setType(TransactionType type) {
            this.type = type;
            return this;
        }
        public Builder setAccountType(TransactionType type) {
            this.type = type;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setFromAccountNumber(String fromAccountNumber) {
            this.fromAccountNumber = fromAccountNumber;
            return this;
        }

        public Builder setToAccountNumber(String toAccountNumber) {
            this.toAccountNumber = toAccountNumber;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setMetadata(Map<String, Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        // Build method to return the final object
        public TransferRequest build() {
            return new TransferRequest(this);
        }
    }

    @JsonIgnore
    public boolean isValid(){
        return amount != null &&
                amount.compareTo(BigDecimal.ZERO)>0 &&
                StringUtils.isNotBlank(fromAccountNumber.toString());
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
