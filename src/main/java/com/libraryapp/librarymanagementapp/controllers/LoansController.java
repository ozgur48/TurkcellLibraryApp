package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.loan.request.CreateLoanRequest;
import com.libraryapp.librarymanagementapp.dto.loan.response.CreatedLoanResponse;
import com.libraryapp.librarymanagementapp.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/library/loans")
public class LoansController {
    private final LoanService loanService;

    public LoansController(LoanService loanService) {
        this.loanService = loanService;
    }
    @PostMapping
    public ResponseEntity<CreatedLoanResponse> createLoan(CreateLoanRequest createLoanRequest){
        CreatedLoanResponse res = loanService.addLoan(createLoanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}
