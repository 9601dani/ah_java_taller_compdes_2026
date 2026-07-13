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
    private final StoringUserOutputPort storingUserOutputPort;

    public FindUserUseCase(FindingUserByUserNameOutputPort findingUserByUserNameOutputPort, StoringUserOutputPort storingUserOutputPort) {
        this.findingUserByUserNameOutputPort = findingUserByUserNameOutputPort;
        this.storingUserOutputPort = storingUserOutputPort;
    }

    @Override
    public User findUser(String id) throws EntityNotFoundException {
        Optional<User> userOptional = this.findingUserByUserNameOutputPort.findByUserName(id);

        if (userOptional.isEmpty())
            throw new EntityNotFoundException("No se encontró el nombre de usuario: " + id);

        User user = userOptional.get();

        User userUpdated = new User(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getDescription().toUpperCase()
        );

        this.storingUserOutputPort.save(userUpdated);

        return userOptional.get();
    }
}
