package com.example.expensetracker.service;

import com.example.expensetracker.entity.Account;
import com.example.expensetracker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Handles user registration.
     */
    public Map<String, Object> register(Account account) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
                response.put("message", "Username already exists");
                return response;
            }

            accountRepository.save(account);
            response.put("message", "Registration successful");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "An error occurred during registration: " + e.getMessage());
            return response;
        }
    }

    /**
     * Handles user login.
     */
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
    
        if (!accountOptional.isPresent()) {
            response.put("message", "Invalid username or password");
            return response;
        }
    
        Account account = accountOptional.get();
    
        if (account.getPassword().equals(password)) {
            response.put("message", "Login successful");
            response.put("accountId", account.getAccountId()); // ✅ added
        } else {
            response.put("message", "Invalid username or password");
        }
    
        return response;
    }
    
}
