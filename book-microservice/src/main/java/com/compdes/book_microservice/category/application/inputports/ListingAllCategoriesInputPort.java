package com.compdes.book_microservice.category.application.inputports;

import java.util.List;

import com.compdes.book_microservice.category.domain.Category;

public interface ListingAllCategoriesInputPort {
    List<Category> getAllCategories();
}
