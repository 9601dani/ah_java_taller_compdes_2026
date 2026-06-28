package com.compdes.book_microservice.book.domain;

import lombok.Value;

@Value
public class BookTitle {

    private String title;

    public BookTitle(String title) {
        if (title.isBlank())
            throw new IllegalArgumentException("El título no puede estar vacío.");

        this.title = title;
    }

    public static BookTitle toDomain(String title) {
        return new BookTitle(title);
    }
}
