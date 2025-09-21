package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.entity.Fine;
import com.libraryapp.librarymanagementapp.entity.Member;
import com.libraryapp.librarymanagementapp.enums.BookStatus;
import com.libraryapp.librarymanagementapp.repository.FineRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class FineBusinessRules {
    private final FineRepository fineRepository;

    public FineBusinessRules(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public BigDecimal calculateDelayFine(LocalDate returnDate, LocalDate dueDate, BigDecimal dailyRate) {
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return dailyRate.multiply(BigDecimal.valueOf(daysLate));
        }
        return BigDecimal.ZERO;
    }
    public void membertMustNotHasFine(Member member){
        if(member.getFines() !=null){
            throw new BusinessException("Ödenmemiş cezası olan üye yeni ödünç/rezervasyon yapamaz.");
        }
    }
    public double calculateBookDamagePrice(Book book, double bookPrice, double jail){
        if(book.getBookStatus() == BookStatus.DAMAGED || book.getBookStatus() == BookStatus.LOST){
            return bookPrice + jail;
        }else{
            throw new BusinessException("Kitap sorunsuz");
        }
    }


}
