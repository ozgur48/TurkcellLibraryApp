package com.libraryapp.librarymanagementapp.entity;

import com.libraryapp.librarymanagementapp.enums.LoanStatus;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private LocalDate loanDate;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name="loan_status", nullable = false)
    private LoanStatus loanStatus;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Loan(int id, LocalDate dueDate, LocalDate returnDate, LocalDate loanDate, Staff staff, Book book, LoanStatus loanStatus, Member member) {
        this.id = id;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.staff = staff;
        this.book = book;
        this.loanStatus = loanStatus;
        this.member = member;
    }

    public Loan(){}

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public Book getBook() {
        return book;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
