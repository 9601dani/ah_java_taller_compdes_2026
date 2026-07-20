package com.compdes.book_microservice.category.infrastructure.inputadapters.rest.dto;

import com.compdes.book_microservice.category.domain.Category;

import lombok.Value;

@Value
public class CategoryResponseDto {
    private String name;

    public static CategoryResponseDto fromDomain(Category category){
        return new CategoryResponseDto(category.getName().getName());
    }
}
