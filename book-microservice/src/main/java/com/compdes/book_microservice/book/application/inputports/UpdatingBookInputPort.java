package com.compdes.book_microservice.book.application.inputports;

import com.compdes.book_microservice.book.application.usecases.updatebook.UpdateBookDto;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.UUID;

public interface UpdatingBookInputPort {

    Book update(UUID id, UpdateBookDto dto) throws EntityNotFoundException;
}
