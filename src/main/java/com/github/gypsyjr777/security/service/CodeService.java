package com.github.gypsyjr777.security.service;

import com.github.gypsyjr777.security.entity.UserCode;
import com.github.gypsyjr777.security.repository.UserCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CodeService {
    private final UserCodeRepository userCodeRepository;

    @Autowired
    public CodeService(UserCodeRepository userCodeRepository) {
        this.userCodeRepository = userCodeRepository;
    }

    public String generateCode() {
        //nnn nnn - pattern
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 6) {
            sb.append(random.nextInt(9));
        }
        sb.insert(3, " ");
        return sb.toString();
    }

    public void saveNewCode(UserCode userCode) {
        if (userCodeRepository.findByCode(userCode.getCode()) == null) {
            userCodeRepository.save(userCode);
        }
    }

    public Boolean verifyCode(String code) {
        UserCode userCode = userCodeRepository.findByCode(code);
        return userCode != null && !userCode.isExpired();
    }

}
