package com.libraryapp.librarymanagementapp.entity;

import com.libraryapp.librarymanagementapp.enums.LoanStatus;
import com.libraryapp.librarymanagementapp.enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @JoinColumn(name="member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name="book_id")
    @ManyToOne
    private Book book;

    private LocalDate reservedAt;

    private LocalDate expireAt;

    public LocalDate getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDate reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDate expireAt) {
        this.expireAt = expireAt;
    }


    public Reservation(int id, ReservationStatus status, Member member, Book book, LocalDate expireAt, LocalDate reservedAt) {
        this.id = id;
        this.status = status;
        this.member = member;
        this.book = book;
        this.expireAt = expireAt;
        this.reservedAt = reservedAt;
    }
    public Reservation(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


}
