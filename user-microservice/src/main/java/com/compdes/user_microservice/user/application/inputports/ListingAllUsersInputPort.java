package com.compdes.user_microservice.user.application.inputports;

import com.compdes.user_microservice.user.domain.User;

import java.util.List;

public interface ListingAllUsersInputPort {

    List<User> listAllUsers();
}
