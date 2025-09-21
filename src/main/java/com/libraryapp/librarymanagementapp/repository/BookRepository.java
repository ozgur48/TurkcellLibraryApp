package com.libraryapp.librarymanagementapp.repository;

import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBooksById(int id);
    Optional<Book> findByTitle(String title);
    Optional<Book> findByIsbnAndTitleAndAuthorAndBookStatus(String isbn, String title, Author author, BookStatus bookStatus);

}
