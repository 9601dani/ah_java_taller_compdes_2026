package com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;

@Repository
public interface CategoryDbEntityJpaRepository extends JpaRepository<CategoryDbEntity, String> {
}
