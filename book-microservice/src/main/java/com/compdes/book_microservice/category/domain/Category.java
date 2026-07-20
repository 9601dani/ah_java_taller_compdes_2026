package com.compdes.book_microservice.category.domain;

import com.compdes.book_microservice.common.domain.annotations.DomainEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@DomainEntity
@AllArgsConstructor
@Getter
public class Category {
    private CategoryName name;

    public static Category toDomain(String name) {
        return new Category(CategoryName.fromString(name));
    }
}
