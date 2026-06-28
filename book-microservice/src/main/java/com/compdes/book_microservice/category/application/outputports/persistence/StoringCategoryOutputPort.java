package com.compdes.book_microservice.category.application.outputports.persistence;


import com.compdes.book_microservice.category.domain.Category;

public interface StoringCategoryOutputPort {
    Category save(Category category);
}
