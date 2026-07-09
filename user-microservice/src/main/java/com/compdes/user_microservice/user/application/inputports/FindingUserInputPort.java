package com.compdes.user_microservice.user.application.inputports;

import com.compdes.user_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.user_microservice.user.domain.User;

public interface FindingUserInputPort {

    User findUser(String id) throws EntityNotFoundException;
}
