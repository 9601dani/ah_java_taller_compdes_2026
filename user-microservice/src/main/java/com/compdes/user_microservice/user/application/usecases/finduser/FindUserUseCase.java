package com.compdes.user_microservice.user.application.usecases.finduser;

import com.compdes.user_microservice.common.application.annotations.UseCase;
import com.compdes.user_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.user_microservice.user.application.inputports.FindingUserInputPort;
import com.compdes.user_microservice.user.application.outputports.persistence.FindingUserByUserNameOutputPort;
import com.compdes.user_microservice.user.application.outputports.persistence.StoringUserOutputPort;
import com.compdes.user_microservice.user.domain.User;

import java.util.Optional;

@UseCase
public class FindUserUseCase implements FindingUserInputPort {

    private final FindingUserByUserNameOutputPort findingUserByUserNameOutputPort;

    public FindUserUseCase(FindingUserByUserNameOutputPort findingUserByUserNameOutputPort) {
        this.findingUserByUserNameOutputPort = findingUserByUserNameOutputPort;
    }

    @Override
    public User findUser(String id) throws EntityNotFoundException {
        Optional<User> userOptional = this.findingUserByUserNameOutputPort.findByUserName(id);

        if (userOptional.isEmpty())
            throw new EntityNotFoundException("No se encontró el nombre de usuario: " + id);

        return userOptional.get();
    }
}
