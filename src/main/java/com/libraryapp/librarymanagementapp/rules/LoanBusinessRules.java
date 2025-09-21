package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.entity.Fine;
import com.libraryapp.librarymanagementapp.entity.Loan;
import com.libraryapp.librarymanagementapp.entity.Member;
import com.libraryapp.librarymanagementapp.enums.BookStatus;
import com.libraryapp.librarymanagementapp.enums.MemberShipLevel;
import com.libraryapp.librarymanagementapp.repository.LoanRepository;
import org.hibernate.type.descriptor.jdbc.DecimalJdbcType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class LoanBusinessRules {
    private final LoanRepository loanRepository;

    public LoanBusinessRules(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void checkBookAvailable(Book book){
        if(book.getAvailableCopies() <= 0 ){
            throw new BusinessException("Kitabın erişebilir kopyası yok!!!");
        }
    }
    public void checkIsPaid(Member member){
        if(member.getFines() != null){
            throw new BusinessException("Borcu olduğu için ödünç alamaz!!!");
        }
    }

    public void checkDuplicateLoan(int memberId, String isbn){
        Loan existingLoan = loanRepository.findByMemberIdAndBookIsbnAndReturnDateIsNull(memberId, isbn).orElse(null);
        if(existingLoan != null){
            throw new BusinessException("Bu kullanıcı bu kitabı iade etmeden tekrar alamaz.");
        }
    }
    public LocalDate calculateDueDate(Member member, Loan loan){
        if(member.getMemberShipLevel() == MemberShipLevel.STANDART){
            return loan.getLoanDate().plusDays(14);
        }
        else if(member.getMemberShipLevel() == MemberShipLevel.GOLD){
            return loan.getLoanDate().plusDays(21);
        }
        else{
            throw new BusinessException("Desteklenmeyen memberShipLevel!!!");
        }
    }
    public BigDecimal calculateFine(Loan loan){
        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
            return BigDecimal.valueOf(daysLate * 5); // günlük 5₺
        }
        return BigDecimal.ZERO;

    }
    public void increasingAvailableCopyAfterReturned(Loan loan){
        if(loan.getBook().getBookStatus() == BookStatus.CHECKED_OUT){
             loan.getBook().setAvailableCopies(loan.getBook().getAvailableCopies()+1);
             loan.getBook().setBookStatus(BookStatus.AVAILABLE);
        }
    }
}
