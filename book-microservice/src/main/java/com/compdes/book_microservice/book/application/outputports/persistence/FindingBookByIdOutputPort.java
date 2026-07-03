package com.compdes.book_microservice.book.application.outputports.persistence;

import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface FindingBookByIdOutputPort {

    Optional<Book> findBookById(UUID id) throws EntityNotFoundException;
}
