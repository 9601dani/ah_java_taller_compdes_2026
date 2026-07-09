package com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.entity;

import com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BookDbEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;
    private String title;
    private String authorName;
    private LocalDate publicationDate;
    private String state;
    private String category;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public BookDbEntity(UUID id, String title, String authorName, LocalDate publicationDate, String state, String category) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.publicationDate = publicationDate;
        this.state = state;
        this.category = category;
    }
}
