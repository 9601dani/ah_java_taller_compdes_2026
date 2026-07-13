package com.compdes.user_microservice.user.application.usecases.listusers;

import com.compdes.user_microservice.common.application.annotations.UseCase;
import com.compdes.user_microservice.user.application.inputports.ListingAllUsersInputPort;
import com.compdes.user_microservice.user.application.outputports.persistence.FindingAllUsersOutputPort;
import com.compdes.user_microservice.user.domain.User;

import java.util.List;

@UseCase
public class ListAllUsersUseCase implements ListingAllUsersInputPort {

    private final FindingAllUsersOutputPort findingAllUsersOutputPort;

    public ListAllUsersUseCase(FindingAllUsersOutputPort findingAllUsersOutputPort) {
        this.findingAllUsersOutputPort = findingAllUsersOutputPort;
    }

    @Override
    public List<User> listAllUsers() {
        return this.findingAllUsersOutputPort.findAllUsers();
    }
}
