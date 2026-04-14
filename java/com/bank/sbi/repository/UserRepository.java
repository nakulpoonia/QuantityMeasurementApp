package com.bank.sbi.repository;

import com.bank.sbi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByAccountNumber(String accountNumber);
    boolean existsByUsername(String username);
    boolean existsByAccountNumber(String accountNumber);
}
