package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.entity.Member;
import com.libraryapp.librarymanagementapp.entity.Reservation;
import com.libraryapp.librarymanagementapp.enums.ReservationStatus;
import com.libraryapp.librarymanagementapp.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class ReservationBusinessRules {
    private final ReservationRepository reservationRepository;

    public ReservationBusinessRules(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void checkIfReservationAllowed(Book book){
        if(book.getAvailableCopies()  == 0){
            throw new BusinessException("Kitap için rezervasyon yapılabilir");
        }
    }
    public void mustNotHaveAnotherActiveReservation(int bookId, int memberId, ReservationStatus reservationStatus){
        boolean exists;
        exists = reservationRepository.existsByMemberIdAndBookIdAndStatus(bookId, memberId, reservationStatus.APPROVED);
        if(exists){
            throw new BusinessException("Üyenin aynı kitap için zaten aktif rezervasyonu var.");
        }
    }

}
