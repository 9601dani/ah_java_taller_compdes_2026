package com.compdes.user_microservice.user.domain;

import com.compdes.user_microservice.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@DomainEntity
@AllArgsConstructor
@Getter
public class User {

    private UserName username;
    private FirstName firstName;
    private String lastName;
    private String description;

}
