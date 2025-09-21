package com.libraryapp.librarymanagementapp.rules;

import com.libraryapp.librarymanagementapp.core.exception.type.BusinessException;
import com.libraryapp.librarymanagementapp.entity.Author;
import com.libraryapp.librarymanagementapp.entity.Book;
import com.libraryapp.librarymanagementapp.enums.BookStatus;
import com.libraryapp.librarymanagementapp.repository.BookRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

@Component
public class BookBusinessRules {
    private final BookRepository bookRepository;

    public BookBusinessRules(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void bookMustNoExistWithSameId(int id){
        Book bookWithSameId = bookRepository.findBooksById(id).orElse(null);
        if(bookWithSameId != null){
            throw new RuntimeException("Bu id'de kitap bulunmaktadır!!");
        }
    }
    public Book bookMustNotExistWithGivenId(int id){
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Bu id ile bir kitap bulunamadı" + id));
    }
    public void bookMustNotExistsWithSameTitle(String title){
        Book bookWithSameTitle = bookRepository.findByTitle(title).orElse(null);
        if(bookWithSameTitle != null){
            throw new RuntimeException("Bu title kitap bulunmaktadır.");
        }
    }
    public Book mustExist(int id){
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Bu ID'lı kitap bulunamadı: ID->" + id));
    }
    public Book mustExistWithTitle(String title){
        return bookRepository.findByTitle(title).orElseThrow(()-> new BusinessException("Bu title ile kitap bulunamadı"));
    }
    public Book mustExistWithIsbnTitleAuthorBookStatus(String isbn, String title, Author author, BookStatus bookStatus){
        return bookRepository.findByIsbnAndTitleAndAuthorAndBookStatus(isbn, title, author, bookStatus).orElseThrow(()->new BusinessException("İstediğin veride (title, isbn, author, bookStatus) kitap bulunamadı."));
    }

    public void availableCopiesMustNotMoreThanTotalCopies(Book book){
        if(book.getAvailableCopies() > book.getTotalCopies() && book.getAvailableCopies() < 0){
            //negatif kontrole dto da validation ile sağlanıyor
            throw new BusinessException(" availableCopies hiçbir koşulda totalCopies’i aşamaz ve negatif olamaz.");
        }
    }
    public void bookStatusIsAvailableOrNot(Book book){
        if(book.getBookStatus() == BookStatus.CHECKED_OUT || book.getBookStatus() == BookStatus.INACTIVE || book.getBookStatus() == BookStatus.RESERVED){
            throw new BusinessException("Kitap ödünç alınamaz ya da rezerve edilemez!!!");
        }
    }
    public void bookGeneralCopiesMustNotNegative(Book book){
        if(book.getAvailableCopies() < 0 || book.getTotalCopies() < 0){
            throw new BusinessException("Kopya sayısı 0'dan küçük olamaz");
        }
    }


}
