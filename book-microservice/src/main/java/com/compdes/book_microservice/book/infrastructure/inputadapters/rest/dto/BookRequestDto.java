package com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto;

import com.compdes.book_microservice.book.application.usecases.createbook.CreateBookDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;

@Value
public class BookRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String authorName;

    @NotNull
    private LocalDate publicationDate;

    @NotBlank
    private String state;

    @NotBlank
    private String category;

    public CreateBookDto toDomain() { // toApplication
        return new CreateBookDto(
                this.title,
                this.authorName,
                this.publicationDate,
                this.state,
                this.category
        );
    }
}
