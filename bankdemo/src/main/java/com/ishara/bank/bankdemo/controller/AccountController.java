package com.ishara.bank.bankdemo.controller;

import com.ishara.bank.bankdemo.dto.BalanceResponse;
import com.ishara.bank.bankdemo.dto.TransactionResponse;
import com.ishara.bank.bankdemo.dto.TransferRequest;
import com.ishara.bank.bankdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String accountd){
        return ResponseEntity.ok(accountService.getBalance(accountd));
    }

    @GetMapping("/{accountId}/deposit")
    public ResponseEntity<TransactionResponse> deposit(@PathVariable String accountId, @RequestParam String amount){
        return ResponseEntity.ok(accountService.deposit(accountId, new BigDecimal(amount)));
    }
    @GetMapping("/{accountId}/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@PathVariable String accountId, @RequestParam String amount){
        return ResponseEntity.ok(accountService.deposit(accountId, new BigDecimal(amount)));
    }
    @GetMapping("/{accountId}/printStatement")
    public ResponseEntity<TransactionResponse> printStatement(@PathVariable String accountId, @RequestParam String date){
        return ResponseEntity.ok(accountService.printStatement(accountId, date));
    }
}
