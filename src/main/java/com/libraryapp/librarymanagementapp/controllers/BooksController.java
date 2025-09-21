package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.book.request.CreateBookRequest;
import com.libraryapp.librarymanagementapp.dto.book.response.BookResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.CreatedBookResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.GetBookByIsbnTitleAuthorBookStatusResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.UpdateBookByIdResponse;
import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.enums.BookStatus;
import com.libraryapp.librarymanagementapp.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/library/books")
public class BooksController {
    private BookService bookService;
    public BooksController(BookService bookService){
        this.bookService = bookService;
    }
    @PostMapping()
    public ResponseEntity<CreatedBookResponse> addBook(@Valid @RequestBody CreateBookRequest createBookRequest){
       CreatedBookResponse response =   bookService.addBook(createBookRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id){
         return bookService.deleteBookById(id);
    }
    @GetMapping("/findAllBook")
    public List<Book> findAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable int id){
        BookResponse res = bookService.findBookById(id);
        return ResponseEntity.ok(res);
    }
    @PatchMapping("/{id}/delta")
    public UpdateBookByIdResponse updateBookStockById(@PathVariable int id, int delta){
        return bookService.updateBookById(id, delta);
    }
    @GetMapping
    public GetBookByIsbnTitleAuthorBookStatusResponse getBookByIsbnTitleAuthorBookStatusResponse(@RequestParam String isbn, String title, Author author, BookStatus bookStatus){
        return bookService.getBookByIsbnTitleAuthorBookStatus(isbn, title, author, bookStatus);
    }
}
