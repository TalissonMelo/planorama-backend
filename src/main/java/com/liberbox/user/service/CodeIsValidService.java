package com.liberbox.user.service;

import com.liberbox.user.controller.request.CodeIsValidRequest;
import com.liberbox.user.controller.response.CodeIsValidResponse;
import com.liberbox.user.domain.RecoveryCode;
import com.liberbox.user.domain.enums.CodeType;
import com.liberbox.user.domain.enums.InvalidCodeCause;
import com.liberbox.user.repository.RecoveryCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CodeIsValidService {

    private final RecoveryCodeRepository repository;
    private final RecoveryCodeGeneratorService recoveryCodeGeneratorService;


    public CodeIsValidResponse execute(CodeIsValidRequest request) {

        Optional<RecoveryCode> recoveryCodeOptional = repository.findRecoveryCodeByCode(request.code());


        if (recoveryCodeOptional.isEmpty()) {
            return CodeIsValidResponse.of(Map.of(InvalidCodeCause.WRONG_CODE, false), "", "");
        }

        return verifyIfIsValid(recoveryCodeOptional.get(), true);

    }

    public CodeIsValidResponse verifyIfIsValid(RecoveryCode recoveryCode, boolean generateNewCode) {

        if (recoveryCode.getExpirationDate().isBefore(LocalDateTime.now())) {
            return CodeIsValidResponse.of(Map.of(InvalidCodeCause.EXPIRATED, false), "", "");
        }
        if (recoveryCode.isWasUsed()) {
            return CodeIsValidResponse.of(Map.of(InvalidCodeCause.ALREADY_USED, false), "", "");
        }
        recoveryCode.useCode();
        if (generateNewCode) {
            return CodeIsValidResponse.of(Map.of(InvalidCodeCause.IS_VALID, true), generateAndSaveNewCodeForChangePassword(recoveryCode.getEmail(), recoveryCode.getLogin(), recoveryCode.getPhone()), recoveryCode.getEmail());
        } else {
            return CodeIsValidResponse.of(Map.of(InvalidCodeCause.IS_VALID, true), "", recoveryCode.getEmail());
        }
    }


    private String generateAndSaveNewCodeForChangePassword(String email, String login, String phone) {

        final String newCode = recoveryCodeGeneratorService.generateNewCode();

        RecoveryCode recoveryCode = RecoveryCode.of(newCode, CodeType.PASSWORD_CHANGE, 15, email, login, phone);

        repository.save(recoveryCode);

        return newCode;
    }
}