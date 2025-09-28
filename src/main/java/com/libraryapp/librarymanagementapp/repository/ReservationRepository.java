package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Reservation;
import com.libraryapp.librarymanagementapp.enums.MemberStatus;
import com.libraryapp.librarymanagementapp.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    boolean existsByMemberIdAndBookIdAndStatus(Integer memberId, Integer bookId,
                                               ReservationStatus status);

    Optional<Reservation> findFirstByBookIdAndStatusOrderByReservedAtAsc(int book_id, ReservationStatus status);
    List<Reservation> findByStatusAndExpireAtBefore(ReservationStatus status, LocalDate expireAt);

    boolean existsByMemberIdAndBookIdAndStatusIn(Integer member_id, Integer book_id, Collection<ReservationStatus> statuses);
}
