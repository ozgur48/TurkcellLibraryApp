package com.libraryapp.librarymanagementapp.dto.book.response;

import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.enums.BookStatus;
import jakarta.validation.constraints.NotNull;

public class GetBookByIsbnTitleAuthorBookStatusResponse {
    @NotNull
    private String title;
    @NotNull
    private String isbn;
    @NotNull
    private Author author;
    private BookStatus bookStatus;

    public GetBookByIsbnTitleAuthorBookStatusResponse(String title, String isbn, Author author, BookStatus bookStatus) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.bookStatus = bookStatus;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



}
