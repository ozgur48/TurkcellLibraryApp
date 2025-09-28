package com.libraryapp.librarymanagementapp.dto.reservation;

import com.libraryapp.librarymanagementapp.enums.ReservationStatus;

import java.time.LocalDate;

public class ReservationResponse {
    private Integer id;
    private Integer memberId;
    private Integer bookId;
    private ReservationStatus status;
    private LocalDate reservedAt;
    private LocalDate expireAt;

    public ReservationResponse(Integer id, Integer memberId, Integer bookId, ReservationStatus status, LocalDate reservedAt, LocalDate expireAt) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.status = status;
        this.reservedAt = reservedAt;
        this.expireAt = expireAt;
    }

    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer memberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer bookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public ReservationStatus status() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDate reservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDate reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDate expireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
    }
}
