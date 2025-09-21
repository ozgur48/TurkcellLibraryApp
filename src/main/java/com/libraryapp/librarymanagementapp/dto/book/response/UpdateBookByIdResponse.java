package com.libraryapp.librarymanagementapp.dto.book.response;

import com.libraryapp.librarymanagementapp.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateBookByIdResponse {
    @NotBlank
    private String title;
    @NotBlank
    private String language;
    @NotBlank
    private BookStatus bookStatus;
    @NotNull
    @Positive
    private int availableCopies;

    public UpdateBookByIdResponse(String title, String language, BookStatus bookStatus, int availableCopies, int totalCopies) {
        this.title = title;
        this.language = language;
        this.bookStatus = bookStatus;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Positive
    private int totalCopies;
}
