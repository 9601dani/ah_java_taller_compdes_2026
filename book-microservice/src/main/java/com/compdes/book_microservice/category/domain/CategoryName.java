package com.compdes.book_microservice.category.domain;

import java.util.Locale;

import lombok.Value;

@Value
public class CategoryName {

    private String name;

    public CategoryName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio.");

        String normalizedName = name.trim().toLowerCase();
        this.name = normalizedName.substring(0, 1).toUpperCase()
                + normalizedName.substring(1);
    }

    public static CategoryName fromString(String name) {
        return new CategoryName(name);
    }
}
