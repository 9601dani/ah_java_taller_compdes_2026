package com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto;

import com.compdes.book_microservice.book.domain.Book;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class BookResponseDto {

    private UUID id;
    private String title;
    private String authorName;
    private LocalDate publicationDate;
    private Boolean isAvailable;
    private String category;

    public static BookResponseDto fromDomain(Book book) {
        return new BookResponseDto(
                book.getBookId().getId(),
                book.getTitle().getTitle(),
                book.getAuthorName(),
                book.getPublicationDate().getPublicationDate(),
                book.getIsAvailable(),
                book.getCategory().getName()
        );
    }
}
