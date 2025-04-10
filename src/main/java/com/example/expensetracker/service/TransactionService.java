package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expenses;
import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.ExpensesRepository;
import com.example.expensetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpensesRepository expensesRepository;

    public Map<String, Object> addIncome(Map<String, Object> incomeDetails) {
        try {
            Income income = new Income();
            income.setAccountId(Long.valueOf(incomeDetails.get("accountId").toString()).intValue());
            income.setSourceId(Long.valueOf(incomeDetails.get("sourceId").toString()).intValue());
            income.setAmount(Double.parseDouble(incomeDetails.get("amount").toString()));
            income.setEntryDate(LocalDateTime.parse(incomeDetails.get("entryDate").toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            income.setNotes(incomeDetails.get("notes").toString());

            Income savedIncome = incomeRepository.save(income);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Income added successfully");
            response.put("incomeId", savedIncome.getIncomeId());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error adding income: " + e.getMessage(), e);
        }
    }

    public Map<String, Object> addExpense(Map<String, Object> expenseDetails) {
        try {
            Expenses expense = new Expenses();
            expense.setAccountId(Long.valueOf(expenseDetails.get("accountId").toString()).intValue());
            expense.setCategoryId(Long.valueOf(expenseDetails.get("categoryId").toString()).intValue());
            expense.setAmount(Double.parseDouble(expenseDetails.get("amount").toString()));
            expense.setEntryDate(LocalDateTime.parse(expenseDetails.get("entryDate").toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            expense.setNotes(expenseDetails.get("notes").toString());

            Expenses savedExpense = expensesRepository.save(expense);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Expense added successfully");
            response.put("expenseId", savedExpense.getExpenseId());
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error adding expense: " + e.getMessage(), e);
        }
    }
}
