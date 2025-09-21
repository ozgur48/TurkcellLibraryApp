package com.libraryapp.librarymanagementapp.dto.loan.request;

import com.libraryapp.librarymanagementapp.entity.Staff;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class CreateLoanRequest {


    private LocalDate dueDate;


    private LocalDate returnDate;


    private LocalDate loanDate;

    private int staffId;

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

    public CreateLoanRequest(LocalDate dueDate, LocalDate returnDate, LocalDate loanDate, int staffId) {
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.staffId = staffId;

    }
    public CreateLoanRequest(){}


    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
