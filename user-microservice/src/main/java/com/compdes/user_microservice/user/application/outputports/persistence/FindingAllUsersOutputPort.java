package com.compdes.user_microservice.user.application.outputports.persistence;

import com.compdes.user_microservice.user.domain.User;

import java.util.List;

public interface FindingAllUsersOutputPort {

    List<User> findAllUsers();
}
