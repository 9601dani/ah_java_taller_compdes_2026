package com.compdes.book_microservice.book.domain;

import com.compdes.book_microservice.book.application.usecases.updatebook.UpdateBookDto;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@DomainEntity
@AllArgsConstructor
@Getter
public class Book {

    private BookId bookId;
    private BookTitle title;
    private String authorName;
    private BookPublicationDate publicationDate;
    private Boolean isAvailable;
    private Category category;

    public Book updateEntity(UpdateBookDto dto, Category category) {
        this.title = BookTitle.toDomain(dto.getTitle());
        this.authorName = dto.getAuthorName();
        this.publicationDate = BookPublicationDate.fromDomain(dto.getPublicationDate());
        this.isAvailable = true;
        this.category = category;
        return this;
    }

    public Book(Book book, Boolean isAvailable) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.authorName = book.getAuthorName();
        this.publicationDate = book.getPublicationDate();
        this.category = book.getCategory();
        this.isAvailable = isAvailable;
    }
}
