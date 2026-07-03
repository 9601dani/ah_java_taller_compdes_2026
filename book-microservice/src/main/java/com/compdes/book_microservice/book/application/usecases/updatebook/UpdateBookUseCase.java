package com.compdes.book_microservice.book.application.usecases.updatebook;

import com.compdes.book_microservice.book.application.inputports.UpdatingBookInputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.FindingBookByIdOutputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.StoringBookOutputPort;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.application.annotations.UseCase;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;

import java.util.Optional;
import java.util.UUID;

@UseCase
public class UpdateBookUseCase implements UpdatingBookInputPort {

    private final StoringBookOutputPort storingBookOutputPort;
    private final FindingBookByIdOutputPort findingBookByNameOutputPort;
    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;

    public UpdateBookUseCase(StoringBookOutputPort storingBookOutputPort,
                             FindingBookByIdOutputPort findingBookByNameOutputPort,
                             FindingCategoryByNameOutputPort findingCategoryByNameOutputPort) {
        this.storingBookOutputPort = storingBookOutputPort;
        this.findingBookByNameOutputPort = findingBookByNameOutputPort;
        this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
    }

    @Override
    public Book update(UUID id, UpdateBookDto dto) throws EntityNotFoundException {
        Optional<Book> bookFound = this.findingBookByNameOutputPort.findBookById(id);

        if (bookFound.isEmpty())
            throw new EntityNotFoundException("No se encontró el libro " + id);

        String categoryName = dto.getCategory();
        Optional<Category> category = this.findingCategoryByNameOutputPort.findByName(categoryName);

        if (category.isEmpty())
            throw new EntityNotFoundException("No se encontró la categoría " + categoryName + " para el libro");

        Book book = dto.toDomain(id);
        return this.storingBookOutputPort.save(book);
    }
}
