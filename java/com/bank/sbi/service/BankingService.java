package com.bank.sbi.service;

import com.bank.sbi.dto.RegisterRequest;
import com.bank.sbi.dto.TransferRequest;
import com.bank.sbi.dto.UpdateContactRequest;
import com.bank.sbi.exception.InsufficientFundsException;
import com.bank.sbi.model.User;
import com.bank.sbi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BankingService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found : " + username));
    }

    public String register(RegisterRequest req) {
        if (req.initialDeposit() != 1000) {
            throw new IllegalArgumentException("Initial deposit balance must be 1000 INR"); // Corrected message for consistency
        }

        if (userRepository.existsByUsername(req.username())) {
            throw new RuntimeException("username already exists");
        }

        String accountNumber = generateAccountNumber();

        User user = new User();
        user.setUsername(req.username());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setMobile(req.phoneNumber());
        user.setAccountNumber(accountNumber);
        user.setBalance(1000.0);
        userRepository.save(user);
        return accountNumber;
    }

    private String generateAccountNumber() {
        String accNo;
        do {
            accNo = "SBI" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
        } while (userRepository.existsByAccountNumber(accNo));
        return accNo;
    }

    public String deposit(double amount) {
        User user = getCurrentUser();
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
        return "₹" + amount + " deposited successfully. New Balance: ₹" + user.getBalance();
    }

    public String withdraw(double amount) {
        User user = getCurrentUser();
        if (user.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds. Available: ₹" + user.getBalance());
        }
        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);
        return "₹" + amount + " withdrawn successfully. New Balance: ₹" + user.getBalance();
    }

    public String transfer(TransferRequest req) {
        User sender = getCurrentUser();
        if (sender.getBalance() < req.amount()) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        User receiver = userRepository.findByAccountNumber(req.toAccountNumber())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        System.out.println("receiver : " + receiver.getUsername());

        sender.setBalance(sender.getBalance() - req.amount());
        receiver.setBalance(receiver.getBalance() + req.amount());

        userRepository.save(sender);
        userRepository.save(receiver);

        return "Transfer of ₹" + req.amount() + " to " + req.toAccountNumber() + " successful.";
    }

    public String optDebitCard() {
        User user = getCurrentUser();
        if (user.isHasDebitCard()) throw new RuntimeException("You already have a Debit Card");
        user.setHasDebitCard(true);
        userRepository.save(user);
        return "Debit Card issued successfully";
    }

    public String optChequeBook() {
        User user = getCurrentUser();
        if (user.isHasChequeBook()) throw new RuntimeException("You already have a Cheque Book");
        user.setHasChequeBook(true);
        userRepository.save(user);
        return "Cheque Book issued successfully";
    }

    public String optPassbook() {
        User user = getCurrentUser();
        if (user.isHasPassbook()) throw new RuntimeException("You already have a Passbook");
        user.setHasPassbook(true);
        userRepository.save(user);
        return "Passbook issued successfully";
    }

    public String updateContact(UpdateContactRequest req) {
        User user = getCurrentUser();
        if (req.email() != null) user.setEmail(req.email());
        if (req.mobile() != null) user.setMobile(req.mobile());
        userRepository.save(user);
        return "Contact details updated successfully";
    }

    public String closeAccount() {
        User user = getCurrentUser();
        userRepository.delete(user);
        return "Account closed successfully";
    }

    public double checkBalance() {
        return getCurrentUser().getBalance();
    }

}
