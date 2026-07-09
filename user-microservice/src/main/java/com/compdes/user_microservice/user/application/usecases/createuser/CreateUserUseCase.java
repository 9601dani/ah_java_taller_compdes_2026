package com.compdes.user_microservice.user.application.usecases.createuser;

import com.compdes.user_microservice.common.application.annotations.UseCase;
import com.compdes.user_microservice.common.application.exceptions.EntityAlreadyExistsException;
import com.compdes.user_microservice.user.application.inputports.CreatingUserInputPort;
import com.compdes.user_microservice.user.application.outputports.persistence.FindingUserByUserNameOutputPort;
import com.compdes.user_microservice.user.application.outputports.persistence.StoringUserOutputPort;
import com.compdes.user_microservice.user.domain.User;

import java.util.Optional;

@UseCase
public class CreateUserUseCase implements CreatingUserInputPort {

    private final StoringUserOutputPort storingUserOutputPort;
    private final FindingUserByUserNameOutputPort findingUserByUserNameOutputPort;

    public CreateUserUseCase(StoringUserOutputPort storingUserOutputPort, FindingUserByUserNameOutputPort findingUserByUserNameOutputPort) {
        this.storingUserOutputPort = storingUserOutputPort;
        this.findingUserByUserNameOutputPort = findingUserByUserNameOutputPort;
    }

    @Override
    public User save(CreateUserDto userDto) throws EntityAlreadyExistsException {
        String userName = userDto.getUserName();

        Optional<User> userOptional = this.findingUserByUserNameOutputPort.findByUserName(userName);

        if (userOptional.isPresent())
            throw new EntityAlreadyExistsException("El nombre de usuario: " + userName + " ya se encuentra registrado.");

        User user = userDto.toDomain();
        return this.storingUserOutputPort.save(user);
    }
}
