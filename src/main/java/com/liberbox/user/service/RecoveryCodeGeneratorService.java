package com.liberbox.user.service;

import com.liberbox.user.repository.RecoveryCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecoveryCodeGeneratorService {

    private final RecoveryCodeRepository recoveryCodeRepository;

    public String generateNewCode() {

        String code = "";

        do {
            code = createCode();
        } while (doesCodeExist(code));

        return code;
    }

    private boolean doesCodeExist(String code) {
        return recoveryCodeRepository.existsRecoveryCodeByCode(code);
    }

    private static String createCode() {

        final String chars = "ABCDEFGHJKLNOPQRSTUVXYZ234567890";

        SecureRandom random = new SecureRandom();

        return random.ints(5, 0, chars.length())
                .mapToObj(chars::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}