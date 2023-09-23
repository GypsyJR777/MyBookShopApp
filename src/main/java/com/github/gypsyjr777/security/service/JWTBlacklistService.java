package com.github.gypsyjr777.security.service;

import com.github.gypsyjr777.entity.security.JWTBlacklist;
import com.github.gypsyjr777.security.repository.JWTBlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTBlacklistService {
    private final JWTBlacklistRepository jwtBlacklistRepository;

    @Autowired
    public JWTBlacklistService(JWTBlacklistRepository jwtBlacklistRepository) {
        this.jwtBlacklistRepository = jwtBlacklistRepository;
    }

    public void addToken(String token) {
        if (token != null && !token.isBlank()) {
            JWTBlacklist jwtBlacklist = new JWTBlacklist();
            jwtBlacklist.setToken(token);
            jwtBlacklist.setDate(new Date());

            jwtBlacklistRepository.save(jwtBlacklist);
        }
    }

    public boolean isTokenOld(String token) {
        JWTBlacklist jwtBlacklist = jwtBlacklistRepository.findByToken(token);

        return jwtBlacklist != null;
    }
}
