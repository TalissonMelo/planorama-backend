package com.liberbox.user.domain;

import com.liberbox.user.domain.enums.CodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class RecoveryCode {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private CodeType codeType;

    @Column(unique = true)
    private String code;

    private LocalDateTime expirationDate;

    private String email;

    private String phone;

    private String login;

    private boolean wasUsed;

    private RecoveryCode(String code, CodeType codeType, LocalDateTime expirationDate, String email, String login, String phone) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.codeType = codeType;
        this.expirationDate = expirationDate;
        this.wasUsed = false;
        this.email = email;
        this.login = login;
        this.phone = phone;
    }

    public static RecoveryCode of(String code, CodeType codeType, Integer tokenExpirationTimeInMinutes, String email, String login, String phone) {
        return new RecoveryCode(code, codeType, LocalDateTime.now().plusMinutes(tokenExpirationTimeInMinutes), email, login, phone);
    }

    public RecoveryCode useCode() {
        this.wasUsed = true;

        return this;
    }
}
