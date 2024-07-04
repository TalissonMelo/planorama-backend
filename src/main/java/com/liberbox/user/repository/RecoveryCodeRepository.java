package com.liberbox.user.repository;

import com.liberbox.user.domain.RecoveryCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecoveryCodeRepository extends JpaRepository<RecoveryCode, String> {

    boolean existsRecoveryCodeByCode(String code);

    Optional<RecoveryCode> findRecoveryCodeByCode(String code);
}
