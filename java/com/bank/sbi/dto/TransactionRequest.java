package com.bank.sbi.dto;

import jakarta.validation.constraints.Positive;

public record TransactionRequest(@Positive Double amount) {
}
