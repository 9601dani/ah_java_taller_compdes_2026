package com.compdes.loan_microservice.loan.domain;

import lombok.Value;

@Value
public class UserName {

    private String userName;

    public UserName(String userName) {
        if (userName.isBlank())
            throw new IllegalArgumentException("El nombre de usuario es inválido.");

        this.userName = userName;
    }

    public static UserName fromString(String userName) {
        return new UserName(userName);
    }
}
