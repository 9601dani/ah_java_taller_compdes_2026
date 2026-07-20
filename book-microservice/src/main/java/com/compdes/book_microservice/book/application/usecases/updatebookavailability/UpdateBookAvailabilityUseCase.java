package com.compdes.book_microservice.book.application.usecases.updatebookavailability;

import com.compdes.book_microservice.book.application.inputports.FindingBookByIdInputPort;
import com.compdes.book_microservice.book.application.inputports.UpdatingBookAvailabilityInputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.StoringBookOutputPort;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.UUID;

@UseCase
public class UpdateBookAvailabilityUseCase implements UpdatingBookAvailabilityInputPort {

    private final FindingBookByIdInputPort findingBookByIdInputPort;
    private final StoringBookOutputPort storingBookOutputPort;

    public UpdateBookAvailabilityUseCase(FindingBookByIdInputPort findingBookByIdInputPort, StoringBookOutputPort storingBookOutputPort) {
        this.findingBookByIdInputPort = findingBookByIdInputPort;
        this.storingBookOutputPort = storingBookOutputPort;
    }

    @Override
    public Book updateBookAvailability(UUID id) throws EntityNotFoundException {
        Book book = this.findingBookByIdInputPort.findBookById(id);

        // TODO: PREGUNTAR
        Book bookUpdated = new Book(
                book,
                !book.getIsAvailable()
        );

        return this.storingBookOutputPort.save(bookUpdated);
    }
}
