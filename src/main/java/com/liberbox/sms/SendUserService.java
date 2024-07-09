package com.liberbox.sms;

import com.liberbox.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendUserService {

    private final TwilioConfig twilioConfig;

    public void send(String schedule, String userName, String phone) {

        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

        String messageBody = String.format(
                "Sistema liberbox.\n" +
                        "Olá, %s.\n" +
                        "Você foi convidado para participar da agenda: .\n%s",
                userName, schedule
        );


        Message.creator(
                new PhoneNumber("+55" + phone),
                new PhoneNumber(twilioConfig.getAccountNumber()),
                messageBody
        ).create();
    }
}