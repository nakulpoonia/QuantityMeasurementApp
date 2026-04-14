package com.bank.sbi.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // A @ControllerAdvice class should not extend RuntimeException
public class GlobalExceptionHandler {
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficient(InsufficientFundsException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
