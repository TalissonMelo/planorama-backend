package com.liberbox.user.service;

import com.liberbox.user.controller.request.PasswordChangeWithSecretRequest;
import com.liberbox.user.controller.response.CodeIsValidResponse;
import com.liberbox.user.domain.RecoveryCode;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.enums.CodeType;
import com.liberbox.user.domain.enums.InvalidCodeCause;
import com.liberbox.user.repository.RecoveryCodeRepository;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangePasswordService {

    private final RecoveryCodeRepository recoveryCodeRepository;
    private final CodeIsValidService codeIsValidService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public CodeIsValidResponse executeOnlyWithEmail(PasswordChangeWithSecretRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("A user with Email: " +
                        request.email() +
                        " does not exist"));


        Optional<RecoveryCode> recoveryCodeOptional = recoveryCodeRepository.findRecoveryCodeByCode(request.code());


        if (recoveryCodeOptional.isEmpty()) {
            return CodeIsValidResponse.of(Map.of(InvalidCodeCause.WRONG_CODE, false), "", "");
        }

        if (!recoveryCodeOptional.get().getCodeType().equals(CodeType.PASSWORD_CHANGE)) {
            return CodeIsValidResponse.of(Map.of(InvalidCodeCause.WRONG_TYPE, false), "", "");
        }

        var response = codeIsValidService.verifyIfIsValid(recoveryCodeOptional.get(), false);

        user.toUpdatedPassword(passwordEncoder.encode(request.newPassword()));

        userRepository.save(user);

        return response;
    }
}
