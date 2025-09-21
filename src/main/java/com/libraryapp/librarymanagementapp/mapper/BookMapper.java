package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.dto.book.request.CreateBookRequest;
import com.libraryapp.librarymanagementapp.dto.book.response.BookResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.CreatedBookResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.GetBookByIsbnTitleAuthorBookStatusResponse;
import com.libraryapp.librarymanagementapp.dto.book.response.UpdateBookByIdResponse;
import com.libraryapp.librarymanagementapp.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target="author.id", source = "authorId")
    @Mapping(target="publisher.id", source = "publisherId")
    @Mapping(source = "isbn", target = "isbn")
    Book createBookRequestToBook(CreateBookRequest createBookRequest);

    CreatedBookResponse bookToCreateBookResponse(Book Book);
    BookResponse bookToBookResponse(Book book);

    UpdateBookByIdResponse bookToUpdateBookByIdResponse(Book book);
    GetBookByIsbnTitleAuthorBookStatusResponse toGetBookByIsbnTitleAuthorBookStatusResponse(Book book);
}
