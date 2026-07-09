package com.compdes.user_microservice.user.infrastructure.inputadapters.rest.dto;

import com.compdes.user_microservice.user.domain.User;
import lombok.Value;

@Value
public class UserResponseDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String description;

    public static UserResponseDto fromDomain(User user) {
        return new UserResponseDto(
                user.getUsername().getUserName(),
                user.getFirstName().getFirstName(),
                user.getLastName(),
                user.getDescription()
        );
    }
}
