package com.compdes.book_microservice.category.infrastructure.outputadapters.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.compdes.book_microservice.category.application.outputports.persistence.FindingAllCategoriesOutputPort;
import com.compdes.book_microservice.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.compdes.book_microservice.category.application.outputports.persistence.StoringCategoryOutputPort;
import com.compdes.book_microservice.category.domain.Category;
import com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity.CategoryDbEntity;
import com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.entity.mapper.CategoryPersistenceMapper;
import com.compdes.book_microservice.category.infrastructure.outputadapters.persistence.repository.CategoryDbEntityJpaRepository;
import com.compdes.book_microservice.common.infrastructure.annotations.PersistenceAdapter;


import lombok.AllArgsConstructor;

@PersistenceAdapter
@AllArgsConstructor
public class CategoryRepositoryOutputAdapter implements FindingCategoryByNameOutputPort, StoringCategoryOutputPort,
FindingAllCategoriesOutputPort{

    private final CategoryDbEntityJpaRepository categoryDbEntityJpaRepository;
    private final CategoryPersistenceMapper categoryPersistenceMapper;

    
    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByName(String name){
        return categoryDbEntityJpaRepository.findById(name)
        .map(categoryPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Category save(Category category){
        CategoryDbEntity savedCategory = categoryDbEntityJpaRepository.save(categoryPersistenceMapper.toDbEntity(category));

        return categoryPersistenceMapper.toDomain(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategories(){
        return categoryDbEntityJpaRepository.findAll()
        .stream()
        .map(categoryPersistenceMapper::toDomain)
        .toList();
    }
}
