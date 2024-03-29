package com.github.gypsyjr777.security.service;

import com.github.gypsyjr777.security.entity.UserCode;
import com.github.gypsyjr777.security.repository.UserCodeRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SmsService {

    @Value("${twilio.ACCOUNT_SID}")
    private String ACCOUNT_SID;

    @Value("${twilio.ACCOUNT_SID}")
    private String AUTH_TOKEN;

    @Value("${twilio.ACCOUNT_SID}")
    private String TWILIO_NUMBER;

    private final UserCodeRepository userCodeRepository;

    @Autowired
    public SmsService(UserCodeRepository userCodeRepository) {
        this.userCodeRepository = userCodeRepository;
    }

    public String sendSecretCodeSms(String contact) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String formattedContact = contact.replaceAll("[()-]]", "");
        String generatedCode = generateCode();
        Message.creator(
                new PhoneNumber(formattedContact),
                new PhoneNumber(TWILIO_NUMBER),
                "Your secret code is: " + generatedCode
        ).create();
        return generatedCode;
    }

    private String generateCode() {
        //nnn nnn - pattern
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 6) {
            sb.append(random.nextInt(9));
        }
        sb.insert(3, " ");
        return sb.toString();
    }

    public void saveNewCode(UserCode smsCode) {
        if (userCodeRepository.findByCode(smsCode.getCode()) == null) {
            userCodeRepository.save(smsCode);
        }
    }

    public Boolean verifyCode(String code) {
        UserCode smsCode = userCodeRepository.findByCode(code);
        return (smsCode != null && !smsCode.isExpired());
    }
}
