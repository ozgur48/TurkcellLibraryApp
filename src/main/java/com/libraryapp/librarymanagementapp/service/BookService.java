package com.libraryapp.librarymanagementapp.service;

import com.libraryapp.librarymanagementapp.dto.book.request.CreateBookRequest;
import com.libraryapp.librarymanagementapp.dto.book.response.BookResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.CreatedBookResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.GetBookByIsbnTitleAuthorBookStatusResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.UpdateBookByIdResponse;
import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.enums.BookStatus;
import com.libraryapp.librarymanagementapp.mapper.BookMapper;
import com.libraryapp.librarymanagementapp.repository.BookRepository;
import com.libraryapp.librarymanagementapp.rules.BookBusinessRules;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookBusinessRules bookBusinessRules;
    private final BookMapper bookMapper;

    BookService(BookRepository bookRepository, BookBusinessRules bookBusinessRules){
        this.bookRepository = bookRepository;
        this.bookBusinessRules = bookBusinessRules;
        this.bookMapper = BookMapper.INSTANCE;
    }
    // create Book
    public CreatedBookResponse addBook(CreateBookRequest createBookRequest){
        bookBusinessRules.bookMustNotExistsWithSameTitle(createBookRequest.getTitle());

        Book book = bookMapper.createBookRequestToBook(createBookRequest);
        book = bookRepository.save(book);
        return bookMapper.bookToCreateBookResponse(book);
    }

    // delete Book
    public ResponseEntity<Void> deleteBookById(int id){
        Book b = bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book not exist with id" + id));
        bookRepository.delete(b);
        return ResponseEntity.noContent().build();
    }

    // list all book
    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public BookResponse findBookById(int id){
        Book book = bookBusinessRules.mustExist(id);
        BookMapper bookMapper = BookMapper.INSTANCE;
        return bookMapper.bookToBookResponse(book);
    }
    public UpdateBookByIdResponse updateBookById(int id, int delta){
        Book book = bookBusinessRules.mustExist(id);
            book.setTotalCopies(book.getTotalCopies() + delta);
            book.setAvailableCopies(book.getAvailableCopies() + delta);
            bookBusinessRules.bookGeneralCopiesMustNotNegative(book);
            bookRepository.save(book);
        return bookMapper.bookToUpdateBookByIdResponse(book);
    }

    public GetBookByIsbnTitleAuthorBookStatusResponse getBookByIsbnTitleAuthorBookStatus(String isbn, String title, Author author, BookStatus bookStatus){
        Book book = bookBusinessRules.mustExistWithIsbnTitleAuthorBookStatus(isbn, title, author, bookStatus);
        return bookMapper.toGetBookByIsbnTitleAuthorBookStatusResponse(book);
    }

}
