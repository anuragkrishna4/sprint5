package com.kiit.library.mapper;

import com.kiit.library.dto.BookDto;
import com.kiit.library.model.Book;

public class BookMapper {

    public static BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .year(book.getYear())
                .build();
    }

    public static Book toEntity(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .year(dto.getYear())
                .build();
    }
}
