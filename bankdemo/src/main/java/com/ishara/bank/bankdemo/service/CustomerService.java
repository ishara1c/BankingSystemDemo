package com.ishara.bank.bankdemo.service;

import com.ishara.bank.bankdemo.AccountType;
import com.ishara.bank.bankdemo.entity.Account;
import com.ishara.bank.bankdemo.entity.Customer;
import com.ishara.bank.bankdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {
@Autowired
private CustomerRepository customerRepository;
    private Optional<Customer> createNewCustomer(String firstName, String lastName,String dob) {
        Customer c = new Customer();
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setDob(Date.valueOf("19950512"));
        c.setId(001L);

        customerRepository.save(c);
        return customerRepository.findById(c.getId());
    }
}
