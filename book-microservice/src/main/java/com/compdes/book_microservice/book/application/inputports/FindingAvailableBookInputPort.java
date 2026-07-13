package com.compdes.book_microservice.book.application.inputports;

import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface FindingAvailableBookInputPort {

    Book findAvailableBook(UUID id) throws EntityNotFoundException;
}
