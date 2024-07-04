package com.liberbox.sms;

import com.liberbox.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwilioService  {

    private final TwilioConfig twilioConfig;

    public void sendVerificationCode(String toPhoneNumber, String userName, String verificationCode) {

        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

        String messageBody = String.format(
                "Sistema liberbox.\nConfirme o código de verificação.\nOlá, %s.\nEsse é o seu código de verificação.\n%s",
                userName, verificationCode
        );

        Message.creator(
                new PhoneNumber("+55" + toPhoneNumber),
                new PhoneNumber(twilioConfig.getAccountNumber()),
                messageBody
        ).create();
    }
}