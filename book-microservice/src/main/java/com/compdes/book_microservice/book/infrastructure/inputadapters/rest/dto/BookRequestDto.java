package com.compdes.book_microservice.book.infrastructure.inputadapters.rest.dto;

import com.compdes.book_microservice.book.application.usecases.createbook.CreateBookDto;
import com.compdes.book_microservice.book.application.usecases.updatebook.UpdateBookDto;
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
    private Boolean isAvailable;

    @NotBlank
    private String category;

    public CreateBookDto toDomain() { // toApplication
        return new CreateBookDto(
                this.title,
                this.authorName,
                this.publicationDate,
                this.isAvailable,
                this.category
        );
    }

    public UpdateBookDto toUpdateDomain() {
        return new UpdateBookDto(
                this.title,
                this.authorName,
                this.publicationDate,
                this.isAvailable,
                this.category
        );
    }
}
