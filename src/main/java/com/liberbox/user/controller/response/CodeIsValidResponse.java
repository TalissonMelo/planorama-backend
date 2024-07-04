package com.liberbox.user.controller.response;

import com.liberbox.user.domain.enums.InvalidCodeCause;

import java.util.Map;

public record CodeIsValidResponse(boolean isValid, InvalidCodeCause invalidCodeCause, String newCode) {

    public static CodeIsValidResponse of(Map<InvalidCodeCause, Boolean> validMap, String newCode) {

        return new CodeIsValidResponse(validMap.values()
                .stream()
                .findFirst()
                .get(),
                validMap.keySet()
                        .stream()
                        .findFirst()
                        .get(),
                newCode);
    }
}
