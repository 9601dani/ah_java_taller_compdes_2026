package com.compdes.user_microservice.user.infrastructure.outputadapters.persistence.entity.mapper;

import com.compdes.user_microservice.user.domain.FirstName;
import com.compdes.user_microservice.user.domain.User;
import com.compdes.user_microservice.user.domain.UserName;
import com.compdes.user_microservice.user.infrastructure.outputadapters.persistence.entity.UserDbEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public User toDomain(UserDbEntity userDbEntity) {
        return new User(
                UserName.fromString(userDbEntity.getUserName()),
                FirstName.fromString(userDbEntity.getFirstName()),
                userDbEntity.getLastName(),
                userDbEntity.getDescription()
        );
    }

    public UserDbEntity fromDomain(User user) {
        return new UserDbEntity(
                user.getUsername().getUserName(),
                user.getFirstName().getFirstName(),
                user.getLastName(),
                user.getDescription()
        );
    }
}
