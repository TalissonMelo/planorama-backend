package com.liberbox.sms;

import com.liberbox.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final TwilioConfig twilioConfig;

    public void sendVerificationCode(String schedule, String userName, String phone, String email) {

        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

        String messageBody = String.format(
                "Sistema liberbox.\n" +
                        "Olá, %s.\n" +
                        "Você foi convidado para participar da agenda: .\n%s\n" +
                        "Primeiro acesso utilize o email:.\n" +
                        "Email: %s\n" +
                        "Senha: utilize Esqueceu a senha?",
                userName, schedule, email
        );


        Message.creator(
                new PhoneNumber("+55" + phone),
                new PhoneNumber(twilioConfig.getAccountNumber()),
                messageBody
        ).create();
    }
}