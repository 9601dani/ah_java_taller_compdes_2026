package com.compdes.book_microservice.book.domain;

import com.compdes.book_microservice.book.application.usecases.updatebook.UpdateBookDto;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@DomainEntity
@AllArgsConstructor
@Getter
public class Book {

    private BookId bookId;
    private BookTitle title;
    private String authorName;
    private BookPublicationDate publicationDate;
    private String state;
    private Category category;

    public Book updateEntity(UpdateBookDto dto, Category category) {
        this.title = BookTitle.toDomain(dto.getTitle());
        this.authorName = dto.getAuthorName();
        this.publicationDate = BookPublicationDate.fromDomain(dto.getPublicationDate());
        this.state = dto.getState();
        this.category = category;
        return this;
    }
}
