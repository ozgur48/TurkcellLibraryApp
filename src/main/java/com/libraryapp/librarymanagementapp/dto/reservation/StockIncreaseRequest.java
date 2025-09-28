package com.libraryapp.librarymanagementapp.dto.reservation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StockIncreaseRequest {
    @NotNull
    private Integer bookId;

    public Integer bookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public int delta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    @Min(1)
    private int delta; // iade ise 1
}
