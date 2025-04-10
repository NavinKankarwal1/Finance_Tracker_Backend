package com.example.expensetracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Source")
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sourceId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isPredefined = false;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // 🟢 Constructors

    public Source() {}

    public Source(String name, Boolean isPredefined, Account account) {
        this.name = name;
        this.isPredefined = isPredefined;
        this.account = account;
    }

    // 🟢 Getters and Setters

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsPredefined() {
        return isPredefined;
    }

    public void setIsPredefined(Boolean isPredefined) {
        this.isPredefined = isPredefined;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
