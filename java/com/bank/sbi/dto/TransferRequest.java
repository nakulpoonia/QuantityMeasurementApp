package com.bank.sbi.dto;

import jakarta.validation.constraints.Positive;

public record TransferRequest(String toAccountNumber, @Positive Double amount) {
}
