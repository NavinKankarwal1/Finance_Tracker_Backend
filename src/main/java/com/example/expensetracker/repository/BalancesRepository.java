package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Balances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalancesRepository extends JpaRepository<Balances, Long> {
    // You can add custom query methods here if needed
}
