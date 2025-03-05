package com.ishara.bank.bankdemo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;
//@Component
public class ConsoleApplication  implements CommandLineRunner, ApplicationListener<ApplicationStartedEvent> {
//    public static void main(String[] args) {
//        SpringApplication.run(ConsoleApplication.class, args);
//    }
    //@Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        System.out.println("You entered: " + number);
    }
    @PostConstruct
    public void init() {
        System.out.println("Bean has been initialized.");
    }
    //@Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("Application has started! Running custom ConsoleListener...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        System.out.println("You entered: " + number);
    }

    //@Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Application is ready! Running custom ConsoleListener...");
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
