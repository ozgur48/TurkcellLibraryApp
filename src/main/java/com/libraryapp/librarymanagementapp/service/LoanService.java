package com.libraryapp.librarymanagementapp.service;

import com.libraryapp.librarymanagementapp.dto.book.request.CreateBookRequest;
import com.libraryapp.librarymanagementapp.dto.book.response.CreatedBookResponse;
import com.libraryapp.librarymanagementapp.dto.loan.request.CreateLoanRequest;
import com.libraryapp.librarymanagementapp.dto.loan.response.CreatedLoanResponse;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.entity.Loan;
import com.libraryapp.librarymanagementapp.mapper.LoanMapper;
import com.libraryapp.librarymanagementapp.repository.LoanRepository;
import com.libraryapp.librarymanagementapp.rules.LoanBusinessRules;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanBusinessRules loanBusinessRules;
    private final LoanMapper loanMapper;

    public LoanService(LoanRepository loanRepository, LoanBusinessRules loanBusinessRules, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanBusinessRules = loanBusinessRules;
        this.loanMapper = loanMapper;
    }

    public CreatedLoanResponse addLoan(CreateLoanRequest createLoanRequest){
        Loan loan = loanMapper.createLoanRequestToLoan(createLoanRequest);
        loan = loanRepository.save(loan);
        return loanMapper.loanToCreateLoanResponse(loan);
    }
}
