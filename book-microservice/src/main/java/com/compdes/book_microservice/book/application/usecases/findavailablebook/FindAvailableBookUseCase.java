package com.compdes.book_microservice.book.application.usecases.findavailablebook;

import com.compdes.book_microservice.book.application.inputports.FindingAvailableBookInputPort;
import com.compdes.book_microservice.book.application.usecases.findbook.FindBookByIdUseCase;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.UUID;

@UseCase
public class FindAvailableBookUseCase implements FindingAvailableBookInputPort {

    private final FindBookByIdUseCase findBookByIdUseCase;

    public FindAvailableBookUseCase(FindBookByIdUseCase findBookByIdUseCase) {
        this.findBookByIdUseCase = findBookByIdUseCase;
    }

    @Override
    public Book findAvailableBook(UUID id) throws EntityNotFoundException {
        Book book = this.findBookByIdUseCase.findBookById(id);

        if (!book.getIsAvailable())
            throw new EntityNotFoundException("El libro no está disponible.");

        return book;
    }
}
