package com.example.expensetracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Integer incomeId;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "source_id", nullable = false)
    private Integer sourceId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Column(name = "notes")
    private String notes;

    //Constructors

    public Income() {}

    public Income(Integer accountId, Integer sourceId, Double amount, LocalDateTime entryDate, String notes) {
        this.accountId = accountId;
        this.sourceId = sourceId;
        this.amount = amount;
        this.entryDate = entryDate;
        this.notes = notes;
    }

    //Getters and Setters

    public Integer getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(Integer incomeId) {
        this.incomeId = incomeId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
