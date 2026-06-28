package com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDbEntity {

    @Id
    private String name;

/*     @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updateAt; */
}
