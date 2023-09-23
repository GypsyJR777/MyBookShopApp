package com.github.gypsyjr777.security.repository;

import com.github.gypsyjr777.entity.security.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeRepository extends JpaRepository<UserCode, Long> {
    UserCode findByCode(String code);
}
