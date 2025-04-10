package com.example.expensetracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Balances")
public class Balances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer balanceId;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private BalanceType type;

    @Column(nullable = false, columnDefinition = "REAL DEFAULT 0")
    private Float amount = 0f;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isPredefined = false;

    //Constructors
    public Balances() {}

    public Balances(Account account, String name, BalanceType type, Float amount, String notes, Boolean isPredefined) {
        this.account = account;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.notes = notes;
        this.isPredefined = isPredefined;
    }

    //Getters and Setters

    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BalanceType getType() {
        return type;
    }

    public void setType(BalanceType type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIsPredefined() {
        return isPredefined;
    }

    public void setIsPredefined(Boolean isPredefined) {
        this.isPredefined = isPredefined;
    }
}
