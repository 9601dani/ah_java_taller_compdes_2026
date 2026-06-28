package com.compdes.book_microservice.book.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
public class BookPublicationDate {

    private LocalDate publicationDate;

    public BookPublicationDate(LocalDate publicationDate) {
        if (publicationDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("La fecha de publicación no puede ser mayor a la fecha actual");

        this.publicationDate = publicationDate;
    }

    public static BookPublicationDate fromDomain(LocalDate publicationDate) {
        return new BookPublicationDate(publicationDate);
    }
}
