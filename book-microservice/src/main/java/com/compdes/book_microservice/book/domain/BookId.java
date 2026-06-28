package com.compdes.book_microservice.book.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class BookId {

    private UUID id;

    public BookId (UUID id) {
        this.id = id;
    }

    public static BookId generate() {
        return new BookId(UUID.randomUUID());
    }

    public static BookId fromUUID(UUID id) {
        return new BookId(id);
    }
}
