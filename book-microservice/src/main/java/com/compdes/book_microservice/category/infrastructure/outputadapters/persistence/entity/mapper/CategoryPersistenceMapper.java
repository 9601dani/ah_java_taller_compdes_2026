package com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity.mapper;

import org.springframework.stereotype.Component;

import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;

@Component
public class CategoryPersistenceMapper {
    public Category toDomain(CategoryDbEntity dbEntity){
        if(dbEntity == null) return null;
        
        return Category.toDomain(dbEntity.getName());
    }

    public CategoryDbEntity toDbEntity(Category category){
        if(category == null);

        return new CategoryDbEntity(category.getName().getName());
        
    }
}
