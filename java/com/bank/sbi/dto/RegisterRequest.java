package com.bank.sbi.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank String username,
        @NotBlank @Size(min = 6) String password,
        @Email String email,
        @Pattern(regexp = "^[6-9]\\d{9}$", message = "must be a valid phone number") String phoneNumber,
        @Positive Double initialDeposit
) {

}
