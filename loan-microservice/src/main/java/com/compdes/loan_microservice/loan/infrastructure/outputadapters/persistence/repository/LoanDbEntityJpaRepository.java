package com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.repository;

import com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity.LoanDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoanDbEntityJpaRepository extends JpaRepository<LoanDbEntity, UUID> {

    List<LoanDbEntity> findByIsActive(Boolean isActive);

    Optional<LoanDbEntity> findByIsActiveAndBookId(Boolean isActive, UUID bookId);
}
