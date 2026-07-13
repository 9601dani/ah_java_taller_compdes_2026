package com.compdes.book_microservice.book.application.usecases.updatebookavailability;

import com.compdes.book_microservice.book.application.inputports.UpdatingBookAvailabilityInputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.StoringBookOutputPort;
import com.compdes.book_microservice.book.application.usecases.findbook.FindBookByIdUseCase;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.UUID;

@UseCase
public class UpdateBookAvailabilityUseCase implements UpdatingBookAvailabilityInputPort {

    private final FindBookByIdUseCase findBookByIdUseCase;
    private final StoringBookOutputPort storingBookOutputPort;

    public UpdateBookAvailabilityUseCase(FindBookByIdUseCase findBookByIdUseCase, StoringBookOutputPort storingBookOutputPort) {
        this.findBookByIdUseCase = findBookByIdUseCase;
        this.storingBookOutputPort = storingBookOutputPort;
    }

    @Override
    public Book updateBookAvailability(UUID id) throws EntityNotFoundException {
        Book book = this.findBookByIdUseCase.findBookById(id);

        // TODO: PREGUNTAR
        Book bookUpdated = new Book(
                book.getBookId(),
                book.getTitle(),
                book.getAuthorName(),
                book.getPublicationDate(),
                !book.getIsAvailable(),
                book.getCategory()
        );

        return this.storingBookOutputPort.save(bookUpdated);
    }
}
