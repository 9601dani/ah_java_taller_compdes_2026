package com.compdes.user_microservice.user.domain;

import lombok.Value;

@Value
public class FirstName {

    private String firstName;

    public FirstName(String firstName) {
        if (firstName == null)
            throw new IllegalArgumentException("El nombre es obligatorio.");

        this.firstName = firstName;
    }

    public static FirstName fromString(String firstName) {
        return new FirstName(firstName);
    }
}
