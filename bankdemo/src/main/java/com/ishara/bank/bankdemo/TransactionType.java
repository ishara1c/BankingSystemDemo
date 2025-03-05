package com.ishara.bank.bankdemo;

public enum TransactionType {
    CREDIT("CR","Credit Transaction"),
    DEBIT("DB","Debit Transaction"),

    CASH_DEPOSIT("CDP","Cash Deposit"),
    CHEQUE_DEPOSIT("CHD","Cheque Deposit"),
    DIRECT_DEPOSIT("DDP","Cash Deposit"),

    CASH_WITHDRAWAL("CWD","Cash Withdrawal"),
    ATM_WITHDRAWAL("ATW","ATM Withdrawal");

    private final String code;
    private final String description;

    TransactionType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
