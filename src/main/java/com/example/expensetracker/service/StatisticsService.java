package com.example.expensetracker.service;

import com.example.expensetracker.entity.Income;
import com.example.expensetracker.entity.Expenses;
import com.example.expensetracker.repository.IncomeRepository;
import com.example.expensetracker.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpensesRepository expensesRepository;

    @SuppressWarnings("null")
    public Map<String, Object> calculateStatistics(Long accountId) {
        List<Income> incomes = incomeRepository.findByAccountId(accountId);
        List<Expenses> expenses = expensesRepository.findByAccountId(accountId);

        Map<String, Object> result = new HashMap<>();

        if ((incomes == null || incomes.isEmpty()) && (expenses == null || expenses.isEmpty())) {
            result.put("message", "No data available for account ID " + accountId);
            return result;
        }

        double totalIncome = incomes.stream()
                .mapToDouble(income -> income.getAmount() != null ? income.getAmount() : 0.0)
                .sum();

        double totalExpenses = expenses.stream()
                .mapToDouble(expense -> expense.getAmount() != null ? expense.getAmount() : 0.0)
                .sum();

        result.put("totalIncome", totalIncome);
        result.put("totalExpenses", totalExpenses);
        result.put("balance", totalIncome - totalExpenses);
        result.put("message", "Statistics calculated successfully");

        return result;
    }
}
