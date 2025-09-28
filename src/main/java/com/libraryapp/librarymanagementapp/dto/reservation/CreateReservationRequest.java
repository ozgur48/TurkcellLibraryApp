package com.libraryapp.librarymanagementapp.dto.reservation;

import jakarta.validation.constraints.NotNull;

public class CreateReservationRequest {
    @NotNull
    private Integer memberId;

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

    @NotNull
    private Integer bookId;
}
