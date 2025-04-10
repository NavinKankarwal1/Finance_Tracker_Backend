package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expenses;
import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.ExpensesRepository;
import com.example.expensetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionHistoryService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpensesRepository expensesRepository;

    public Map<String, Object> getAllTransactionsByAccountId(Long accountId) {
        try {
            List<Income> incomeHistory = incomeRepository.findByAccountId(accountId);
            List<Expenses> expenseHistory = expensesRepository.findByAccountId(accountId);
    
            Map<String, Object> result = new HashMap<>();
            result.put("incomeHistory", incomeHistory);
            result.put("expenseHistory", expenseHistory);
    
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving transaction history: " + e.getMessage(), e);
        }
    }
    

    public Map<String, String> updateIncome(Long incomeId, Map<String, Object> incomeDetails) {
        try {
            Optional<Income> optionalIncome = incomeRepository.findById(incomeId);
            if (optionalIncome.isPresent()) {
                Income income = optionalIncome.get();

                if (incomeDetails.containsKey("amount")) {
                    income.setAmount(Double.parseDouble(incomeDetails.get("amount").toString()));
                }

                if (incomeDetails.containsKey("notes")) {
                    income.setNotes(incomeDetails.get("notes").toString());
                }

                if (incomeDetails.containsKey("entryDate")) {
                    String entryDateStr = incomeDetails.get("entryDate").toString();
                    income.setEntryDate(LocalDateTime.parse(entryDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }

                if (incomeDetails.containsKey("sourceId")) {
                    income.setSourceId(Integer.parseInt(incomeDetails.get("sourceId").toString()));
                }

                incomeRepository.save(income);
                return Collections.singletonMap("message", "Income record updated successfully.");
            }

            return Collections.singletonMap("message", "Income record not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error updating income record: " + e.getMessage(), e);
        }
    }

    public Map<String, String> updateExpense(Long expenseId, Map<String, Object> expenseDetails) {
        try {
            Optional<Expenses> optionalExpense = expensesRepository.findById(expenseId);
            if (optionalExpense.isPresent()) {
                Expenses expense = optionalExpense.get();

                if (expenseDetails.containsKey("amount")) {
                    expense.setAmount(Double.parseDouble(expenseDetails.get("amount").toString()));
                }

                if (expenseDetails.containsKey("notes")) {
                    expense.setNotes(expenseDetails.get("notes").toString());
                }

                if (expenseDetails.containsKey("entryDate")) {
                    String entryDateStr = expenseDetails.get("entryDate").toString();
                    expense.setEntryDate(LocalDateTime.parse(entryDateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }

                if (expenseDetails.containsKey("categoryId")) {
                    expense.setCategoryId(Integer.parseInt(expenseDetails.get("categoryId").toString()));
                }

                expensesRepository.save(expense);
                return Collections.singletonMap("message", "Expense record updated successfully.");
            }

            return Collections.singletonMap("message", "Expense record not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error updating expense record: " + e.getMessage(), e);
        }
    }

    public Map<String, String> deleteIncome(Long incomeId) {
        try {
            if (incomeRepository.existsById(incomeId)) {
                incomeRepository.deleteById(incomeId);
                return Collections.singletonMap("message", "Income record deleted successfully.");
            }
            return Collections.singletonMap("message", "Income record not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting income record: " + e.getMessage(), e);
        }
    }

    public Map<String, String> deleteExpense(Long expenseId) {
        try {
            if (expensesRepository.existsById(expenseId)) {
                expensesRepository.deleteById(expenseId);
                return Collections.singletonMap("message", "Expense record deleted successfully.");
            }
            return Collections.singletonMap("message", "Expense record not found.");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting expense record: " + e.getMessage(), e);
        }
    }
}
