package com.libraryapp.librarymanagementapp.dto.book.response;

import com.libraryapp.librarymanagementapp.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreatedBookResponse {
    @NotBlank
    private String title;

    @NotBlank
    private String language;

    @NotBlank
    private BookStatus bookStatus;
    public CreatedBookResponse(){}
    public CreatedBookResponse(String title, String language, BookStatus status, int availableCopies) {
        this.title = title;
        this.language = language;
        this.bookStatus = bookStatus;
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }


    @NotNull
    @Positive
    private int availableCopies;


}
