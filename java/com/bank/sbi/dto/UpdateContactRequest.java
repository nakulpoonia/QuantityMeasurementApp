package com.bank.sbi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UpdateContactRequest(@Email String email, @Pattern(regexp = "^[6-9]\\d{9}$") String mobile) {
}
