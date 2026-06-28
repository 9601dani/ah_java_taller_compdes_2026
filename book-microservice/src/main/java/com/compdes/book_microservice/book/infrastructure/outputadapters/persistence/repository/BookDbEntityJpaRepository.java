package com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.repository;

import com.compdes.book_microservice.book.infrastructure.outputadapters.persistence.entity.BookDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookDbEntityJpaRepository extends JpaRepository<BookDbEntity, UUID> {
}
