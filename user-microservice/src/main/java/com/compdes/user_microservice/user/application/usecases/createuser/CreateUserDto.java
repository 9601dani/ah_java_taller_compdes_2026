package com.compdes.user_microservice.user.application.usecases.createuser;

import com.compdes.user_microservice.user.domain.FirstName;
import com.compdes.user_microservice.user.domain.User;
import com.compdes.user_microservice.user.domain.UserName;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateUserDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String description;

    public User toDomain() {
        return new User(
                UserName.fromString(userName),
                FirstName.fromString(firstName),
                lastName,
                description
        );
    }
}
