package com.liberbox.user.service;

import com.liberbox.user.controller.request.SendEmailRequest;
import com.liberbox.user.controller.response.EmailResponse;
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

    public EmailResponse execute(SendEmailRequest request) {

        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new IllegalArgumentException("User does not exist"));

        String newCode = recoveryCodeGeneratorService.generateNewCode();

        RecoveryCode recoveryCode = RecoveryCode.of(newCode, CodeType.PASSWORD_RECOVERY, 15, user.getEmail(), user.getEmail(), user.getPhone());

        recoveryCodeRepository.save(recoveryCode);

        return new EmailResponse(execute(user.getEmail()));
    }

    public static String execute(String email) {
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];

        if (username.length() > 3) {
            String visiblePart = username.substring(0, 3);
            String maskedPart = "*".repeat(username.length() - 3);
            username = visiblePart + maskedPart;
        }

        String[] domainParts = domain.split("\\.", 2);
        String domainFirstPart = domainParts[0];
        String domainRemainingPart = domainParts.length > 1 ? domainParts[1] : "";

        if (domainFirstPart.length() > 1) {
            String visibleDomainPart = domainFirstPart.substring(domainFirstPart.length() - 3);
            String maskedDomainPart = "*".repeat(domainFirstPart.length() - 1);
            domainFirstPart = maskedDomainPart + visibleDomainPart;
        }

        String maskedEmail = username + "@" + domainFirstPart + "." + domainRemainingPart;
        return maskedEmail;
    }
}
