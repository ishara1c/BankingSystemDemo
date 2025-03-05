package com.ishara.bank.bankdemo.service;

import com.ishara.bank.bankdemo.AccountType;
import com.ishara.bank.bankdemo.TransactionType;
import com.ishara.bank.bankdemo.dto.TransactionResponse;
import com.ishara.bank.bankdemo.dto.TransferRequest;
import com.ishara.bank.bankdemo.entity.Account;
import com.ishara.bank.bankdemo.entity.Customer;
import com.ishara.bank.bankdemo.entity.Transaction;
import com.ishara.bank.bankdemo.repository.AccountRepository;
import com.ishara.bank.bankdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    public TransactionResponse transfer(TransferRequest request) {
        Account fromAccount = null;
        Account toAccount = null;

            fromAccount = accountRepository.findByAccountNumber(request.getFromAccountNumber())
                    .orElseThrow();;

            toAccount = accountRepository.findByAccountNumber(request.getToAccountNumber())
                    .orElseThrow();

        //validate balance
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        //save transactions
        Transaction debitTx = new Transaction(fromAccount, request.getAmount(), TransactionType.DEBIT);
        Transaction creditTx = new Transaction(toAccount, request.getAmount(), TransactionType.CREDIT);

        transactionRepository.save(debitTx);
        transactionRepository.save(creditTx);

        return new TransactionResponse.Builder().setTransactionId(debitTx.getId()).build();
    }

}
