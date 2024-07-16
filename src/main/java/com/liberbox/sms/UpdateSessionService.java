package com.liberbox.sms;

import com.liberbox.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSessionService {

    private final TwilioConfig twilioConfig;

    public void sendVerificationCode(String schedule, String userName, String scheduleDate, String descriptions, String phone) {

        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

        String messageBody = String.format(
                "Sistema liberbox.\n" +
                        "Olá, %s.\n" +
                        "Seu agendamento em %s para o dia %s foi atualizado.\n" +
                        "Detalhes do agendamento:\n%s",
                userName, schedule, scheduleDate, descriptions
        );


        Message.creator(
                new PhoneNumber("+55" + phone),
                new PhoneNumber(twilioConfig.getAccountNumber()),
                messageBody
        ).create();
    }
}