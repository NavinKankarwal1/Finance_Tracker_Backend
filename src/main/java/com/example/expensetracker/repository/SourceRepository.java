package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<Source, Long> {
    // Add custom queries here if needed
}
