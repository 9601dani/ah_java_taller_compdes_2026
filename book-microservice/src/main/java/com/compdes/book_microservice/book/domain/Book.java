package com.compdes.book_microservice.book.domain;

import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

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

}
