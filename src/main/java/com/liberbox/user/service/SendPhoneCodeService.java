package com.liberbox.user.service;

import com.liberbox.user.controller.request.SendEmailRequest;
import com.liberbox.user.controller.response.PhoneResponse;
import com.liberbox.user.domain.RecoveryCode;
import com.liberbox.user.domain.User;
import com.liberbox.user.domain.enums.CodeType;
import com.liberbox.user.repository.RecoveryCodeRepository;
import com.liberbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendPhoneCodeService {

    private final UserRepository userRepository;
    private final RecoveryCodeRepository recoveryCodeRepository;
    private final RecoveryCodeGeneratorService recoveryCodeGeneratorService;

    public PhoneResponse execute(SendEmailRequest request) {

        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new IllegalArgumentException("User does not exist"));

        String newCode = recoveryCodeGeneratorService.generateNewCode();

        RecoveryCode recoveryCode = RecoveryCode.of(newCode, CodeType.PASSWORD_RECOVERY, 15, user.getEmail(), user.getEmail(), user.getPhone());

        recoveryCodeRepository.save(recoveryCode);

        return new PhoneResponse(maskPhoneNumber(user.getPhone()));
    }

    public static String maskPhoneNumber(String phone) {
        if (phone.length() < 6) {
            throw new IllegalArgumentException("O número de telefone deve ter pelo menos 6 caracteres.");
        }

        String firstFive = phone.substring(0, 5);
        String lastOne = phone.substring(phone.length() - 1);
        return firstFive + "*******" + lastOne;
    }
}
