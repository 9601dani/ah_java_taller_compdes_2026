package com.compdes.book_microservice.book.infrastructure.outputadapters.persistence;

import com.compdes.book_microservice.book.application.outputports.persistence.FindingAllBooksOutputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.FindingBookByIdOutputPort;
import com.compdes.book_microservice.book.application.outputports.persistence.StoringBookOutputPort;
import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.entity.BookDbEntity;
import com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.entity.mapper.BookPersistenceMapper;
import com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.repository.BookDbEntityJpaRepository;
import com.compdes.book_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.book_microservice.common.infrastructure.annotations.PersistenceAdapter;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@AllArgsConstructor
public class BookRepositoryOutputAdapter implements StoringBookOutputPort, FindingBookByIdOutputPort, FindingAllBooksOutputPort {

    private final BookPersistenceMapper bookPersistenceMapper;
    private final BookDbEntityJpaRepository bookDbEntityJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(UUID id) throws EntityNotFoundException {
        return this.bookDbEntityJpaRepository.findById(id)
                .map(bookPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Book save(Book book) {
        BookDbEntity entity = this.bookPersistenceMapper.fromDomain(book);

        BookDbEntity bookSaved = this.bookDbEntityJpaRepository.save(entity);

        return this.bookPersistenceMapper.toDomain(bookSaved);
    }

    @Override
    public List<Book> findAllBooks() {
        return this.bookDbEntityJpaRepository.findAll()
                .stream()
                .map(bookPersistenceMapper::toDomain)
                .toList();
    }
}
