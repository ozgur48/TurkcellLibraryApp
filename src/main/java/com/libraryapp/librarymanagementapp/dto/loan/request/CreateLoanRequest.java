package com.libraryapp.librarymanagementapp.dto.loan.request;

import com.libraryapp.librarymanagementapp.entity.Staff;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CreateLoanRequest {

    @Temporal(TemporalType.TIMESTAMP)   // DB'de hem tarih hem saat saklar
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loanDate;

    private int staffId;

    public CreateLoanRequest(Date dueDate, Date returnDate, Date loanDate, int staffId) {
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.loanDate = loanDate;
        this.staffId = staffId;

    }
    public CreateLoanRequest(){}

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
