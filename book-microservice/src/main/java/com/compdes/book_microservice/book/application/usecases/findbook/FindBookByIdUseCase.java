package com.compdes.book_microservice.book.application.usecases.findbook;

import com.compdes.book_microservice.book.application.inputports.FindingBookByIdInputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.FindingBookByIdOutputPort;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;

@UseCase
public class FindBookByIdUseCase implements FindingBookByIdInputPort {

    public final FindingBookByIdOutputPort findingBookByIdOutputPort;

    public FindBookByIdUseCase(FindingBookByIdOutputPort findingBookByIdOutputPort) {
        this.findingBookByIdOutputPort = findingBookByIdOutputPort;
    }

    @Override
    public Book findBookById(UUID id) throws EntityNotFoundException {
        Optional<Book> optionalBook = this.findingBookByIdOutputPort.findBookById(id);

        if (optionalBook.isEmpty())
            throw new EntityNotFoundException("No se encontró el libro con id: " + id);

        return optionalBook.get();
    }
}
