package com.libraryapp.librarymanagementapp.dto.book.request;

import com.libraryapp.librarymanagementapp.enums.BookStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CreateBookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String language;

    @Enumerated(EnumType.STRING)
    @Column(name="book_satus", nullable = false)
    private BookStatus bookStatus;

    @NotNull
    private String isbn;

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
    public CreateBookRequest(){}

    public CreateBookRequest(String title, String language, BookStatus bookStatus, String isbn, Date year, int totalCopies, int publisherId, int availableCopies, int authorId) {
        this.title = title;
        this.language = language;
        this.bookStatus = bookStatus;
        this.isbn = isbn;
        this.year = year;
        this.totalCopies = totalCopies;
        this.publisherId = publisherId;
        this.availableCopies = availableCopies;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    private Date year;

    @Positive
    private int totalCopies;

    private int publisherId;

    @NotNull
    @Positive
    private int availableCopies;

    private int authorId;

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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
