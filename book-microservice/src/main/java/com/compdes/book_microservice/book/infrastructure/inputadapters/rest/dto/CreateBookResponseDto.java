package com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto;

import com.compdes.book_microservice.book.domain.Book;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateBookResponseDto {

    private String title;
    private String authorName;
    private LocalDate publicationDate;
    private String state;
    private String category;

    public static CreateBookResponseDto fromDomain(Book book) {
        return new CreateBookResponseDto(
                book.getTitle().getTitle(),
                book.getAuthorName(),
                book.getPublicationDate().getPublicationDate(),
                book.getState(),
                book.getCategory().getName()
        );
    }
}
