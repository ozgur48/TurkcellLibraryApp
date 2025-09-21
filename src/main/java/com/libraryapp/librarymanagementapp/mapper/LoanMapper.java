package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.dto.loan.request.CreateLoanRequest;
import com.libraryapp.librarymanagementapp.dto.loan.response.CreatedLoanResponse;
import com.libraryapp.librarymanagementapp.entity.Loan;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface LoanMapper {
    Loan createLoanRequestToLoan(CreateLoanRequest createLoanRequest);
    CreatedLoanResponse loanToCreateLoanResponse(Loan loan);
}
