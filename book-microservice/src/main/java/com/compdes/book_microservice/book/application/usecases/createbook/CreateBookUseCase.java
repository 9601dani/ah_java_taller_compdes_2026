package com.compdes.book_microservice.book.application.usecases.createbook;

import com.compdes.book_microservice.book.application.inputports.CreatingBookInputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.FindingBookByIdOutputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.StoringBookOutputPort;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityAlreadyExistException;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.Optional;

@UseCase
public class CreateBookUseCase implements CreatingBookInputPort {

    private final StoringBookOutputPort storingBookOutputPort;
    private final FindingBookByIdOutputPort findingBookByNameOutputPort;
    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;

    public CreateBookUseCase(StoringBookOutputPort storingBookOutputPort,
                             FindingBookByIdOutputPort findingBookByNameOutputPort,
                             FindingCategoryByNameOutputPort findingCategoryByNameOutputPort) {
        this.storingBookOutputPort = storingBookOutputPort;
        this.findingBookByNameOutputPort = findingBookByNameOutputPort;
        this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
    }

    @Override
    public Book save(CreateBookDto dto) throws EntityAlreadyExistException, EntityNotFoundException {
        String categoryName = dto.getCategory();
        Optional<Category> category = this.findingCategoryByNameOutputPort.findByName(categoryName);

        if (category.isEmpty())
            throw new EntityNotFoundException("No se encontró la categoría " + categoryName + " para el libro");

        Book book = dto.toDomain();
        return storingBookOutputPort.save(book);
    }
}
