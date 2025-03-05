package com.ishara.bank.bankdemo.service;

import com.ishara.bank.bankdemo.AccountType;
import com.ishara.bank.bankdemo.TransactionStatus;
import com.ishara.bank.bankdemo.dto.BalanceResponse;
import com.ishara.bank.bankdemo.dto.TransactionResponse;
import com.ishara.bank.bankdemo.entity.Account;
import com.ishara.bank.bankdemo.entity.Customer;
import com.ishara.bank.bankdemo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Method to deposit an amount into a bank account
     *
     * @param accountNumber The account number where the deposit is made
     * @param amount        The amount to deposit
     * @return The updated Account object after deposit
     */
    public TransactionResponse deposit(String accountNumber, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }

        // Fetch the account from the database using the account number
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElse(
                        createNewAccount(accountNumber, amount, AccountType.SAVINGS, "ACTIVE", null).get());
        if (account == null) {
            return new TransactionResponse.Builder()
                    .setStatus(TransactionStatus.FAILED).build();
        }
        // Update the account balance
        account.setBalance(account.getBalance().add(amount));

        // Save the updated account back to the database
        accountRepository.save(account);
        return new TransactionResponse.Builder().setTransactionId(456767L)
                .setAmount(amount)
                .setStatus(TransactionStatus.COMPLETED).build();
    }

    public TransactionResponse withdraw(String accountNumber, BigDecimal amount) {
        BalanceResponse balance = getBalance(accountNumber);
        return new TransactionResponse.Builder().setTransactionId(456797L)
                .setAmount(balance.getAvailableBalance())
                .setStatus(TransactionStatus.COMPLETED).build();
    }

    public BalanceResponse getBalance(String accountId) {

        Account account = null;
        try {
            account = accountRepository.findByAccountNumber(accountId)
                    .orElseThrow(() -> new AccountNotFoundException());
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new BalanceResponse.Builder()
                .setAccountNumber("123456")
                .setAccountType(AccountType.SAVINGS)
                .setAccountName("John Doe")
                .setCurrency("SGD")
                .setCurrentBalance(new BigDecimal("1500.75"))
                .setAvailableBalance(new BigDecimal("1200.00"))
                .build();

    }

    private Optional<Account> createNewAccount(String accountNumber, BigDecimal amount, AccountType type,
                                               String status, Customer customerId) {
        Customer c = new Customer();
        c.setFirstName("ishara");
        c.setLastName("cooray");
        Account newAccount = new Account(accountNumber, amount, type,
                status, c);
        accountRepository.save(newAccount);
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public TransactionResponse printStatement(String accountNumber, String date) {
        return new TransactionResponse.Builder().build();
    }

    }
