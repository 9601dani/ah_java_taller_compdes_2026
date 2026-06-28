package com.compdes.book_microservice.category.application.outputports.persistence;

import java.util.List;

import com.compdes.book_microservice.category.domain.Category;

public interface FindingAllCategoriesOutputPort {
    List<Category> findAllCategories();
}
