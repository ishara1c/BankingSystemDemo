package com.ishara.bank.bankdemo;

import ch.qos.logback.core.util.StringUtil;
import com.ishara.bank.bankdemo.controller.AccountController;
import com.ishara.bank.bankdemo.controller.CustomerController;
import com.ishara.bank.bankdemo.controller.TransactionController;
import com.ishara.bank.bankdemo.dto.TransactionResponse;
import com.ishara.bank.bankdemo.dto.TransferRequest;
import com.ishara.bank.bankdemo.entity.Customer;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication(scanBasePackages = {"com.ishara.bank.bankdemo"})
public class BankdemoApplication {
    static ApplicationContext ctx;
    static Scanner scanner = new Scanner(System.in);
    private static long counter = 0;

    public static void main(String[] args) {
        SpringApplication.run(BankdemoApplication.class, args);

        System.out.println("application context started.");
        //requestUserInput();
        //	SpringApplication.run(ConsoleApplication.class, args);
    }
    @Bean
    public CountDownLatch countDownLatch() {
        return new CountDownLatch(1);  // Initialize latch with 1 count
    }
    @EventListener
    private static void startProcessing(ApplicationReadyEvent event){
        ctx = event.getApplicationContext();
        System.out.println("Application Context in ApplicationReadyEvent: " + ctx);
//        createCustomer();
        requestUserInput(true);
    }

    private static void createCustomer() {
        CustomerController customerController = ctx.getBean(CustomerController.class);
        Customer c = new Customer();
        c.setFirstName("aaaaaa");
        c.setLastName("cccccccc");
        c.setId(002L);
       // c.setDob(new Date("19851208"));
//        customerController.create(c);
    }
    //ConsoleApplication consoleApplication = new ConsoleApplication();

    private static void requestUserInput(boolean isWelcome) {
        if(!isWelcome){
            System.out.println("Is there anything else you'd like to do?");
        } else {
            System.out.println("Welcome to AwesomeGIC Bank! What would you like to do?");
        }
        System.out.println("[T] Input transactions");
        System.out.println("[I] Define interest rules");
        System.out.println("[P] Print statement");
        System.out.println("[Q] Quit");
        String choice = scanner.nextLine();
        System.out.println("You entered: " + choice);
        switch (choice.toUpperCase()) {
            case "T":
                makeTransfer();
            case "I":
                defineInterestRules();
            case "P":
                printStatement();
            case "Q":
                exitApplication();
        }
    }

    private void printMainMenu() {

    }

    private static boolean makeTransfer() {
        System.out.println("Please enter transaction details in <Date> <Account> <Type> <Amount> format ");
        System.out.println("(or enter blank to go back to main menu):");
        String transferInput = scanner.nextLine();
        if (StringUtils.isBlank(transferInput.trim())) {
            requestUserInput(true);
        }

        String[] inputParam = transferInput.trim().split(" ");
        if (isValidDate(inputParam[0])) {
            String txnId = generateRequestId(inputParam[0]);
            TransferRequest transferRequest = new TransferRequest.Builder().setRequestId(txnId)
                    .setFromAccountNumber(inputParam[1])
                    .setType(inputParam[2].equalsIgnoreCase("D") ? TransactionType.DEBIT
                            : inputParam[2].equalsIgnoreCase("W") ? TransactionType.ATM_WITHDRAWAL
                            : TransactionType.DEBIT)
                    .setAmount(new BigDecimal(inputParam[3]))
                    .build();
            AccountController controller = ctx.getBean(AccountController.class);
            if(inputParam[2].equalsIgnoreCase("D")) {
                ResponseEntity<TransactionResponse> response = controller.deposit(inputParam[1]
                        , inputParam[3]);
                if (response.getStatusCode().is2xxSuccessful()) {
                    requestUserInput(false);
                    return true;
                }
            }
            if(inputParam[2].equalsIgnoreCase("W")) {
                ResponseEntity<TransactionResponse> response = controller.withdraw(inputParam[1]
                        , inputParam[3]);
                if (response.getStatusCode().is2xxSuccessful()) {
                    requestUserInput(false);
                    return true;
                }
            }

        } else {
            System.out.println("Invalid date format");
            requestUserInput(true);
        }
        return false;
    }

    public static synchronized String generateRequestId(String date) {
        return counter < 10 ? date + "0" + counter++ : date + counter++;
    }

    private static void defineInterestRules() {

    }

    private static void printStatement() {
        System.out.println("Please enter account and month to generate the statement <Account> <Year><Month>\n" +
                "(or enter blank to go back to main menu):");

    }

    private static void exitApplication() {
        System.out.println("Thank you for banking with AwesomeGIC Bank.\n" +
                "Have a nice day!");
        System.exit(0);
    }

    public static boolean isValidDate(String dateStr) {
        // Define the date format you want to validate
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);  // Disable lenient parsing (e.g., February 30th won't be allowed)

        try {
            // Try parsing the date
            java.util.Date date = sdf.parse(dateStr);
            // If parsing is successful, check if the input string matches the format exactly
            return dateStr.equals(sdf.format(date));
        } catch (Exception e) {
            // If an exception is thrown, the date is invalid
            return false;
        }
    }
}
