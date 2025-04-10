package com.example.expensetracker.controller;

import com.example.expensetracker.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/transactions")
public class TransactionHistoryController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    // Get Transaction History
    @GetMapping("/history")
    public ResponseEntity<?> getTransactionsByAccountId(@RequestParam("accountId") Long accountId) {
        try {
            Object transactions = transactionHistoryService.getAllTransactionsByAccountId(accountId);
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            e.printStackTrace();
            return buildErrorResponse("Error retrieving transaction history", e);
        }
    }   

    // Update Income
    @PutMapping("/income/{incomeId}")
    public ResponseEntity<?> updateIncome(
            @PathVariable("incomeId") Long incomeId,
            @RequestBody Map<String, Object> incomeDetails) {
        try {
            Object response = transactionHistoryService.updateIncome(incomeId, incomeDetails);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return buildErrorResponse("Error updating income record", e);
        }
    }

    // Update Expense
    @PutMapping("/expense/{expenseId}")
    public ResponseEntity<?> updateExpense(
            @PathVariable("expenseId") Long expenseId,
            @RequestBody Map<String, Object> expenseDetails) {
        try {
            Object response = transactionHistoryService.updateExpense(expenseId, expenseDetails);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return buildErrorResponse("Error updating expense record", e);
        }
    }

    // Delete Income
    @DeleteMapping("/income/{incomeId}")
    public ResponseEntity<?> deleteIncome(@PathVariable("incomeId") Long incomeId) {
        try {
            Object response = transactionHistoryService.deleteIncome(incomeId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return buildErrorResponse("Error deleting income record", e);
        }
    }

    // Delete Expense
    @DeleteMapping("/expense/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable("expenseId") Long expenseId) {
        try {
            Object response = transactionHistoryService.deleteExpense(expenseId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return buildErrorResponse("Error deleting expense record", e);
        }
    }

    // Utility method for consistent error response
    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, Exception e) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", message + ": " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
