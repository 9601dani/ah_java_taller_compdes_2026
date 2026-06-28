package com.compdes.book_microservice.category.domain;

import com.compdes.book_microservice.common.domain.annotations.DomainEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;


@DomainEntity
@AllArgsConstructor
@Getter
public class Category {
    private String name;

}
