package com.libraryapp.librarymanagementapp.entity;

import com.libraryapp.librarymanagementapp.enums.BookStatus;
import jakarta.persistence.*;

import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

@Table(name="books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(max = 150)
    private String title;

    @Column(name = "year", nullable = false)
    private Date year;

    @Column(name = "language", nullable = false)
    @NotBlank
    @Size(max = 150)
    private String language;

    @Column(name = "total_copies", nullable = false)
    @NotNull
    @Positive
    private int totalCopies;

    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status", nullable = false)
    private BookStatus bookStatus;

    @Column(name = "available_copies", nullable = false)
    @NotNull
    @Positive
    private int availableCopies;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name="publisher_id", nullable = false)
    private Publisher publisher;
    public Book(String title, String language, BookStatus bookStatus, int availableCopies) {
        this.title = title;
        this.language = language;
        this.bookStatus = bookStatus;
        this.availableCopies = availableCopies;
    }

    public Book(int id, String title, Date year, String language, int totalCopies, String isbn, BookStatus bookStatus, int availableCopies, Author author, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.language = language;
        this.totalCopies = totalCopies;
        this.isbn = isbn;
        this.bookStatus = bookStatus;
        this.availableCopies = availableCopies;
        this.author = author;
        this.publisher = publisher;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
