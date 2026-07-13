package com.compdes.book_microservice.book.application.usecases.updatebook;

import com.compdes.book_microservice.book.domain.Book;
import com.compdes.book_microservice.book.domain.BookId;
import com.compdes.book_microservice.book.domain.BookPublicationDate;
import com.compdes.book_microservice.book.domain.BookTitle;
import com.compdes.book_microservice.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@AllArgsConstructor
public class UpdateBookDto {

    private String title;
    private String authorName;
    private LocalDate publicationDate;
    private String category;

    public Book toDomain(UUID id) {
        return new Book(
                BookId.fromUUID(id),
                BookTitle.toDomain(this.title),
                this.authorName,
                BookPublicationDate.fromDomain(this.publicationDate),
                true,
                Category.toDomain(this.category)
        );
    }
}
