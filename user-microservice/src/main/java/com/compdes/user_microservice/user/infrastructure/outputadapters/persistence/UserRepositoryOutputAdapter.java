package com.compdes.user_microservice.user.infrastructure.outputadapters.persistence;

import com.compdes.user_microservice.common.application.exceptions.EntityNotFoundException;
import com.compdes.user_microservice.common.infrastructure.annotations.PersistenceAdapter;
import com.compdes.user_microservice.user.application.outputports.persistence.FindingAllUsersOutputPort;
import com.compdes.user_microservice.user.application.outputports.persistence.FindingUserByUserNameOutputPort;
import com.compdes.user_microservice.user.application.outputports.persistence.StoringUserOutputPort;
import com.compdes.user_microservice.user.domain.User;
import com.compdes.user_microservice.user.infrastructure.outputadapters.persistence.entity.UserDbEntity;
import com.compdes.user_microservice.user.infrastructure.outputadapters.persistence.entity.mapper.UserPersistenceMapper;
import com.compdes.user_microservice.user.infrastructure.outputadapters.persistence.repository.UserDbEntityJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
public class UserRepositoryOutputAdapter implements StoringUserOutputPort, FindingUserByUserNameOutputPort, FindingAllUsersOutputPort {

    private final UserPersistenceMapper userPersistenceMapper;
    private final UserDbEntityJpaRepository userDbEntityJpaRepository;

    public UserRepositoryOutputAdapter(UserPersistenceMapper userPersistenceMapper, UserDbEntityJpaRepository userDbEntityJpaRepository) {
        this.userPersistenceMapper = userPersistenceMapper;
        this.userDbEntityJpaRepository = userDbEntityJpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUserName(String username) {
        return this.userDbEntityJpaRepository.findById(username)
                .map(userPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public User save(User user) {
        UserDbEntity userDbEntity = this.userPersistenceMapper.fromDomain(user);
        UserDbEntity userCreated = this.userDbEntityJpaRepository.save(userDbEntity);

        return this.userPersistenceMapper.toDomain(userCreated);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userDbEntityJpaRepository.findAll()
                .stream().map(this.userPersistenceMapper::toDomain)
                .toList();
    }
}
