package com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.entity.mapper;

import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.book.domain.BookId;
import com.compdes.book_microservice.book.domain.BookPublicationDate;
import com.compdes.book_microservice.book.domain.BookTitle;
import com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.entity.BookDbEntity;
import com.compdes.book_microservice.category.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class BookPersistenceMapper {

    public BookDbEntity fromDomain(Book book) {
        return new BookDbEntity(
                book.getBookId().getId(),
                book.getTitle().getTitle(),
                book.getAuthorName(),
                book.getPublicationDate().getPublicationDate(),
                book.getIsAvailable(),
                book.getCategory().getName()
        );
    }

    public Book toDomain(BookDbEntity entity) {
        return new Book(
                BookId.fromUUID(entity.getId()),
                BookTitle.toDomain(entity.getTitle()),
                entity.getAuthorName(),
                BookPublicationDate.fromDomain(entity.getPublicationDate()),
                entity.getIsAvailable(),
                Category.toDomain(entity.getCategory())
        );
    }
}
