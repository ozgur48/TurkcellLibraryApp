package com.libraryapp.librarymanagementapp.service;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.entity.Member;
import com.libraryapp.librarymanagementapp.entity.Reservation;
import com.libraryapp.librarymanagementapp.enums.ReservationStatus;
import com.libraryapp.librarymanagementapp.mapper.ReservationMapper;
import com.libraryapp.librarymanagementapp.repository.BookRepository;
import com.libraryapp.librarymanagementapp.repository.MemberRepository;
import com.libraryapp.librarymanagementapp.repository.ReservationRepository;
import com.libraryapp.librarymanagementapp.rules.ReservationBusinessRules;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationBusinessRules reservationBusinessRules;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public ReservationService(ReservationBusinessRules reservationBusinessRules, ReservationRepository reservationRepository, ReservationMapper reservationMapper, BookRepository bookRepository, MemberRepository memberRepository) {
        this.reservationBusinessRules = reservationBusinessRules;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }
    @Transactional
    public Reservation createReservation(Integer memberId, Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Kitap bulunamadı: " + bookId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException("Üye bulunamadı: " + memberId));

        // Stok varken rezervasyon yapılmaz (doğrudan ödünç verilir)
        if (book.getAvailableCopies() > 0) {
            throw new BusinessException("Stok mevcutken rezervasyon yapılamaz; doğrudan ödünç veriniz.");
        }

        // Aynı üye + aynı kitap için ikinci aktif rezervasyon olmasın
        boolean existsActive = reservationRepository
                .existsByMemberIdAndBookIdAndStatusIn(
                        memberId, bookId, EnumSet.of(ReservationStatus.APPROVED, ReservationStatus.READY_FOR_PICKUP));
        if (existsActive) {
            throw new BusinessException("Üyenin bu kitap için zaten aktif rezervasyonu var.");
        }

        Reservation r = new Reservation();
        r.setMember(member);
        r.setBook(book);
        r.setStatus(ReservationStatus.APPROVED);
        r.setReservedAt(LocalDate.now()); // entity'ye dokunmadan default veriyoruz
        // expireAt ACTIVE iken null kalır
        return reservationRepository.save(r);
    }



    /* === 2) Stok/iade: sıradaki ACTIVE'e 24h pickup ver === */

    @Transactional
    public void onStockIncrease(Integer bookId, int delta) {
        if (delta <= 0) return;

        Book book = bookRepository.getForUpdate(bookId);
        book.setAvailableCopies(book.getAvailableCopies() + delta);

        while (book.getAvailableCopies() > 0) {
            var nextOpt = reservationRepository.findFirstByBookIdAndStatusOrderByReservedAtAsc(
                    bookId, ReservationStatus.APPROVED);
            if (nextOpt.isEmpty()) break;

            Reservation r = nextOpt.get();
            r.setStatus(ReservationStatus.READY_FOR_PICKUP);
            r.setExpireAt(LocalDate.now().plusDays(1)); // LocalDate ile gün bazlı 24h
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
    }
    /* === 3) Kullanıcı geldi: teslim et === */
    @Transactional
    public void fulfillReservation(Integer reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new BusinessException("Rezervasyon bulunamadı: " + reservationId));
        if (r.getStatus() != ReservationStatus.READY_FOR_PICKUP) {
            throw new BusinessException("Rezervasyon teslim almaya hazır değil.");
        }
        r.setStatus(ReservationStatus.FULFILLED);
        r.setExpireAt(null);
        // availableCopies burada değişmez: READY_FOR_PICKUP aşamasında 1 düşürülmüştü
    }

    /* === 4) Süresi dolanları düşür ve kopyayı sıradakine ata ===
      Scheduling’i main class'ta veya bir config'te @EnableScheduling ile açman yeterli. */
    @Scheduled(cron = "0 */5 * * * *") // her 5 dk
    @Transactional
    public void expireAndReallocate() {
        LocalDate today = LocalDate.now();
        List<Reservation> toExpire = reservationRepository
                .findByStatusAndExpireAtBefore(ReservationStatus.READY_FOR_PICKUP, today);

        for (Reservation r : toExpire) {
            r.setStatus(ReservationStatus.EXPIRED);
            r.setExpireAt(null);

            // Serbest kalan 1 kopyayı sıradakine tahsis et
            onStockIncrease(r.getBook().getId(), 1);
        }
    }
}
