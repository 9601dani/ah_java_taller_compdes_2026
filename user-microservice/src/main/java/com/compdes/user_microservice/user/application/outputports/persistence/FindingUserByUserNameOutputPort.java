package com.compdes.user_microservice.user.application.outputports.persistence;

import com.compdes.user_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.user_microservice.user.domain.User;

import java.util.Optional;

public interface FindingUserByUserNameOutputPort {

    Optional<User> findByUserName(String username) throws EntityNotFoundException;
}
