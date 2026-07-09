package com.compdes.user_microservice.user.domain;

import lombok.Value;

@Value
public class UserName {

    private String userName;

    public UserName(String userName) {
        if (userName == null)
            throw new IllegalArgumentException("El nombre de usuario es obligatorio.");

        this.userName = userName;
    }

    public static UserName fromString(String userName) {
        return new UserName(userName);
    }
}
