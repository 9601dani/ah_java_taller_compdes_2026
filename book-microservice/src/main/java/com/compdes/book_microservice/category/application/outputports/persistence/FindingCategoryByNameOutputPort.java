package com.compdes.book_microservice.category.application.outputports.persistence;

import java.util.Optional;

import com.compdes.book_microservice.category.domain.Category;

public interface FindingCategoryByNameOutputPort {
    Optional<Category> findByName(String name);
}
