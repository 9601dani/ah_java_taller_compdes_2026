package com.compdes.book_microservice.book.application.usecases.createbook;

import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.book.domain.BookId;
import com.compdes.book_microservice.book.domain.BookPublicationDate;
import com.compdes.book_microservice.book.domain.BookTitle;
import com.compdes.book_microservice.category.domain.Category;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class CreateBookDto {

    private String title;
    private String authorName;
    private LocalDate publicationDate;
    private String state;
    private String category;

    public Book toDomain() {
        return new Book(
                BookId.generate(),
                BookTitle.toDomain(this.title),
                this.authorName,
                BookPublicationDate.fromDomain(this.publicationDate),
                this.state,
                Category.toDomain(this.category)
        );
    }
}
