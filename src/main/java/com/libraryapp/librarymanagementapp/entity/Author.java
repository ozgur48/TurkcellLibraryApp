package com.libraryapp.librarymanagementapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "authors")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max=100)
    @NotBlank
    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Size(max=100)
    @NotBlank
    @Column(name="last_name", nullable = false, length = 100)
    private String lastName;

    @OneToMany(mappedBy = "author")
    @JsonIgnore // döngüyü ve büyük JSON'u önlemek için
    private List<Book> books;
    public List<Book> getBooks() {return books;}

    public Author(int id, String name, String lastName, List<Book> books) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.books = books;
    }
    public Author(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
