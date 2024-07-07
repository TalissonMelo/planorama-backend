package com.liberbox.user.repository;

import com.liberbox.user.domain.RecoveryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RecoveryCodeRepository extends JpaRepository<RecoveryCode, String> {

    boolean existsRecoveryCodeByCode(String code);


    @Query("SELECT r FROM RecoveryCode r WHERE r.code = :code ")
    Optional<RecoveryCode> findRecoveryCodeByCode(String code);
}
