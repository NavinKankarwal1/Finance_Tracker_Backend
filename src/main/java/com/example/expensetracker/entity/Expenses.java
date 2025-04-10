package com.example.expensetracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer expenseId;

    @Column(name = "account_id", nullable = false)
    private Integer accountId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Column(name = "notes")
    private String notes;

    //Constructors

    public Expenses() {}

    public Expenses(Integer accountId, Integer categoryId, Double amount, LocalDateTime entryDate, String notes) {
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.entryDate = entryDate;
        this.notes = notes;
    }

    //Getters and Setters

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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
