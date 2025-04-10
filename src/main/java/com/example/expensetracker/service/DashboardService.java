package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expenses;
import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.ExpensesRepository;
import com.example.expensetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpensesRepository expensesRepository;

    public Map<String, Object> getDashboardData() {
        List<Income> incomeData = incomeRepository.findAll();
        List<Expenses> expenseData = expensesRepository.findAll();

        Map<String, Object> response = new HashMap<>();

        if (incomeData.isEmpty() && expenseData.isEmpty()) {
            response.put("message", "No data available");
            response.put("income", Collections.emptyList());
            response.put("expenses", Collections.emptyList());
            return response;
        }

        double totalIncome = incomeData.stream()
                .mapToDouble(Income::getAmount)
                .sum();

        double totalExpenses = expenseData.stream()
                .mapToDouble(Expenses::getAmount)
                .sum();

        response.put("totalIncome", totalIncome);
        response.put("totalExpenses", totalExpenses);
        response.put("message", "Dashboard data retrieved successfully");

        return response;
    }
}
