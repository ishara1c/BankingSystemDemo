package com.ishara.bank.bankdemo.repository;

import com.ishara.bank.bankdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
