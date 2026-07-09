package com.compdes.user_microservice.user.infrastructure.outputadapters.persistence.repository;

import com.compdes.user_microservice.user.infrastructure.outputadapters.persistence.entity.UserDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDbEntityJpaRepository extends JpaRepository<UserDbEntity, String> {
}
