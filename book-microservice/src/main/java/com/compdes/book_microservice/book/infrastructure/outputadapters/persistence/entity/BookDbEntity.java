package com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.entity;

import com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDbEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String title;
    private String authorName;
    private LocalDate publicationDate;
    private String state;
    private String category;

}
