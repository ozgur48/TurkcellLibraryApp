package com.libraryapp.librarymanagementapp.entity;

import com.libraryapp.librarymanagementapp.enums.FineType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name="fines")
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Positive
    @Column(name="amount", nullable = false)
    private int amount;

    @NotBlank
    @Column(name="reason")
    private String reason;

    @Column(name = "is_paid", nullable = true)
    private boolean isPaid;

    @Enumerated(EnumType.STRING)
    @Column(name="fine_type", nullable = false)
    private FineType fineType; // lost, damage ?

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="loan_id")
    private Loan loan;

    public Fine(){}

    public Fine(int id, int amount, String reason, FineType fineType, Member member, Loan loan) {
        this.id = id;
        this.amount = amount;
        this.reason = reason;
        this.fineType = fineType;
        this.member = member;
        this.loan = loan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public FineType getFineType() {
        return fineType;
    }

    public void setFineType(FineType fineType) {
        this.fineType = fineType;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}
