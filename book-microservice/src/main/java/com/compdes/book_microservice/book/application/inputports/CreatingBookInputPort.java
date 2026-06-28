package com.compdes.book_microservice.book.application.inputports;

import com.compdes.book_microservice.book.application.usecases.createbook.CreateBookDto;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.exceptions.EntityAlreadyExistException;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

public interface CreatingBookInputPort {

    Book save(CreateBookDto dto) throws EntityAlreadyExistException, EntityNotFoundException;
}
