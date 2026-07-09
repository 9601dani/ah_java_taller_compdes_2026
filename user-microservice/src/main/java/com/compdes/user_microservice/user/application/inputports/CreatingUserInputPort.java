package com.compdes.user_microservice.user.application.inputports;

import com.compdes.user_microservice.common.application.exceptions.EntityAlreadyExistsException;
import com.compdes.user_microservice.user.application.usecases.createuser.CreateUserDto;
import com.compdes.user_microservice.user.domain.User;

public interface CreatingUserInputPort {

    User save(CreateUserDto userDto) throws EntityAlreadyExistsException;
}
