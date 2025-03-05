package com.ishara.bank.bankdemo.controller;

import com.ishara.bank.bankdemo.dto.TransactionResponse;
import com.ishara.bank.bankdemo.dto.TransferRequest;
import com.ishara.bank.bankdemo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Component
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody TransferRequest request){
        return ResponseEntity.ok(transactionService.transfer(request));
    }

}
