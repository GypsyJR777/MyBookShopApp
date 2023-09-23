package com.github.gypsyjr777.security.repository;

import com.github.gypsyjr777.entity.security.JWTBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWTBlacklistRepository extends JpaRepository<JWTBlacklist, Long> {
    JWTBlacklist findByToken(String token);
}
