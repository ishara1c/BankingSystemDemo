package com.ishara.bank.bankdemo.dto;

import com.ishara.bank.bankdemo.TransactionStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class TransactionResponse {
  private Long transactionId;
  private String referenceNumber;
  private TransactionStatus status;
  private LocalDateTime timestamp;
  private BigDecimal amount;
  private BigDecimal balanceAfterTransaction;
  private String message;
  private List<String> errors;
  private TransactionResponse(Builder builder) {
    this.transactionId = builder.transactionId;
    this.referenceNumber = builder.referenceNumber;
    this.status = builder.status;
    this.timestamp = builder.timestamp;
    this.amount = builder.amount;
    this.balanceAfterTransaction = builder.balanceAfterTransaction;
    this.message = builder.message;
    this.errors = builder.errors;
  }
  // Static Builder class
  public static class Builder {
    private Long transactionId;
    private String referenceNumber;
    private TransactionStatus status;
    private LocalDateTime timestamp;
    private BigDecimal amount;
    private BigDecimal balanceAfterTransaction;
    private String message;
    private List<String> errors;

    // Builder setter methods
    public Builder setTransactionId(Long transactionId) {
      this.transactionId = transactionId;
      return this;
    }

    public Builder setReferenceNumber(String referenceNumber) {
      this.referenceNumber = referenceNumber;
      return this;
    }

    public Builder setStatus(TransactionStatus status) {
      this.status = status;
      return this;
    }

    public Builder setTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
      return this;
    }

    public Builder setAmount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    public Builder setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
      this.balanceAfterTransaction = balanceAfterTransaction;
      return this;
    }

    public Builder setMessage(String message) {
      this.message = message;
      return this;
    }

    public Builder setErrors(List<String> errors) {
      this.errors = errors;
      return this;
    }

    // Build method to return the final object
    public TransactionResponse build() {
      return new TransactionResponse(this);
    }
  }

  public Long getTransactionId() {
    return transactionId;
  }
}
