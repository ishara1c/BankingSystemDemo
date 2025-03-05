package com.ishara.bank.bankdemo;

import com.ishara.bank.bankdemo.AccountType;
import com.ishara.bank.bankdemo.TransactionType;
import com.ishara.bank.bankdemo.dto.TransactionResponse;
import com.ishara.bank.bankdemo.dto.TransferRequest;
import com.ishara.bank.bankdemo.entity.Account;
import com.ishara.bank.bankdemo.entity.Customer;
import com.ishara.bank.bankdemo.entity.Transaction;
import com.ishara.bank.bankdemo.repository.AccountRepository;
import com.ishara.bank.bankdemo.repository.TransactionRepository;
import com.ishara.bank.bankdemo.service.TransactionService;
import io.micrometer.common.util.StringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TransactionServiceTest {
    @InjectMocks
    private TransactionService transactionService; // Service to be tested
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;
    private Account mockAccount1;
    private Account mockAccount2;
    private Transaction debitTx = null;
    private Transaction creditTx = null;
    private Customer customer;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//create mock customer object
        Customer customer1 =  new Customer();
        customer1.setFirstName("isha");
        customer1.setLastName("coo");

        Customer customer2 =  new Customer();
        customer2.setFirstName("aaa");
        customer2.setLastName("ffoo");

        mockAccount1 = new Account("AC001",new BigDecimal("1000.00"), AccountType.SAVINGS,"ACTIVE",
                customer1);
        mockAccount1.setId(1L);
        mockAccount2 = new Account("AC002",new BigDecimal("2000.00"), AccountType.SAVINGS,"ACTIVE",
                customer2);
        mockAccount2.setId(2L);
        debitTx = new Transaction(mockAccount1, new BigDecimal("1000.00"),TransactionType.DEBIT);
                creditTx = new Transaction(mockAccount2,new BigDecimal("2000.00"),TransactionType.CREDIT);

    }
    @Test
    void contextLoads() {
    }
    @Test
    public void testTransfer(){
        // Arrange
        when(accountRepository.findByAccountNumber("AC001")).thenReturn(Optional.of(mockAccount1));
        when(accountRepository.findByAccountNumber("AC002")).thenReturn(Optional.of(mockAccount2));

        TransferRequest transferRequest = new TransferRequest.Builder().setRequestId("20250227-01")
                .setAmount(new BigDecimal("1000.00"))
                .setType(TransactionType.CREDIT)
                .setFromAccountNumber("AC001")
                .setToAccountNumber("AC002").build();
        TransactionResponse transactionResponse = transactionService.transfer(transferRequest);
        Assert.assertTrue(transactionResponse.getTransactionId()>0);
    }

}
