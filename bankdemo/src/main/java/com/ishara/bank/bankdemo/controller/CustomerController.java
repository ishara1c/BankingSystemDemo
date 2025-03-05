package com.ishara.bank.bankdemo.controller;

import com.ishara.bank.bankdemo.dto.BalanceResponse;
import com.ishara.bank.bankdemo.entity.Customer;
import com.ishara.bank.bankdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Customer customer){
        Optional<Customer> c = customerRepository.findById(customer.getId());
        if(!c.isPresent()){
            customerRepository.save(customer);
        }
        return ResponseEntity.ok("SUCCESS");
    }


}
