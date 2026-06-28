package com.compdes.book_microservice.book.application.usecases.createbook;

import com.compdes.book_microservice.book.application.inputports.CreatingBookInputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.FindingBookByNameOutputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.StoringBookOutputPort;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityAlreadyExistException;

@UseCase
public class CreateBookUseCase implements CreatingBookInputPort {

    private final StoringBookOutputPort storingBookOutputPort;
    private final FindingBookByNameOutputPort findingBookByNameOutputPort;

    public CreateBookUseCase(StoringBookOutputPort storingBookOutputPort, FindingBookByNameOutputPort findingBookByNameOutputPort) {
        this.storingBookOutputPort = storingBookOutputPort;
        this.findingBookByNameOutputPort = findingBookByNameOutputPort;
    }

    @Override
    public Book save(CreateBookDto dto) throws EntityAlreadyExistException {
        // TODO: VERIFY UNIQUE ID OR NAME
        Book book = dto.toDomain();
        return storingBookOutputPort.save(book);
    }
}
