package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Account;
import com.example.expensetracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint to handle user registration.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        try {
            Map<String, Object> response = authService.register(account);

            if ("Registration successful".equals(response.get("message"))) {
                response.put("status", HttpStatus.CREATED.value());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                response.put("status", HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> error = new HashMap<>();
            error.put("message", "An error occurred: " + e.getMessage());
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * Endpoint to handle user login.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            Map<String, Object> response = authService.login(username, password);

            if ("Login successful".equals(response.get("message"))) {
                response.put("status", HttpStatus.OK.value());
                return ResponseEntity.ok(response);
            } else {
                response.put("status", HttpStatus.UNAUTHORIZED.value());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> error = new HashMap<>();
            error.put("message", "An error occurred: " + e.getMessage());
            error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}