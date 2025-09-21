package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Reservation;
import com.libraryapp.librarymanagementapp.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsByMemberIdAndBookIdAndStatus(Integer memberId, Integer bookId,
                                               ReservationStatus status);
}
