package com.compdes.user_microservice.user.infrastructure.inputadapters.rest.dto;

import com.compdes.user_microservice.user.application.usecases.createuser.CreateUserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserRequestDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String firstName;

    private String lastName;

    private String description;

    public CreateUserDto toDomain() { // TOAPPLICATION
        return new CreateUserDto(
                this.userName,
                this.firstName,
                this.lastName,
                this.description
        );
    }
}
