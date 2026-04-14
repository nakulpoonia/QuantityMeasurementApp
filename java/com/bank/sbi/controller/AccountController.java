package com.bank.sbi.controller;

import com.bank.sbi.dto.TransactionRequest;
import com.bank.sbi.dto.TransferRequest;
import com.bank.sbi.dto.UpdateContactRequest;
import com.bank.sbi.service.BankingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class    AccountController {
    private final BankingService bankingService;

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance() {
        return ResponseEntity.ok(bankingService.checkBalance());
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@Valid @RequestBody TransactionRequest req) {
        return ResponseEntity.ok(bankingService.deposit(req.amount()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@Valid @RequestBody TransactionRequest req) {
        return ResponseEntity.ok(bankingService.withdraw(req.amount()));

    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest req) {
        return ResponseEntity.ok(bankingService.transfer(req));
    }

    @PostMapping("/opt/debitcard")
    public ResponseEntity<String> optDebitCard() {
        return ResponseEntity.ok(bankingService.optDebitCard());
    }

    @PostMapping("/opt/chequebook")
    public ResponseEntity<String> optChequeBook() {
        return ResponseEntity.ok(bankingService.optChequeBook());
    }

    @PostMapping("/opt/passbook")
    public ResponseEntity<String> optPassbook() {
        return ResponseEntity.ok(bankingService.optPassbook());
    }

    @PutMapping("/contact")
    public ResponseEntity<String> updateContact(@Valid @RequestBody UpdateContactRequest req) {
        return ResponseEntity.ok(bankingService.updateContact(req));
    }

    @DeleteMapping("/close")
    public ResponseEntity<String> closeAccount() {
        return ResponseEntity.ok(bankingService.closeAccount());
    }
}
