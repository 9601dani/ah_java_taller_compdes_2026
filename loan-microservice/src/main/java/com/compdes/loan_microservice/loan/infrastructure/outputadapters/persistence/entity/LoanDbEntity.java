package com.compdes.loan_microservice.loan.infrastructure.outputadapters.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loans")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class LoanDbEntity {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private UUID id;

    private String userName;

    @Column(columnDefinition = "CHAR(36)")
    private UUID bookId;
    private Boolean isActive;
    private LocalDate initDate;
    private LocalDate endDate;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public LoanDbEntity(UUID id, String userName, UUID bookId, Boolean isActive, LocalDate initDate, LocalDate endDate) {
        this.id = id;
        this.userName = userName;
        this. bookId = bookId;
        this.isActive = isActive;
        this.initDate = initDate;
        this.endDate = endDate;
    }
}
