package com.compdes.user_microservice.user.application.outputports.persistence;


import com.compdes.user_microservice.user.domain.User;

public interface StoringUserOutputPort {

    User save(User user);
}
