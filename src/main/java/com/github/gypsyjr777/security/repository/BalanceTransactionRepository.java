package com.github.gypsyjr777.security.repository;

import com.github.gypsyjr777.entity.payments.BalanceTransactionEntity;
import com.github.gypsyjr777.security.model.BookstoreUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceTransactionRepository extends JpaRepository<BalanceTransactionEntity, Integer> {
    List<BalanceTransactionEntity> findAllByUser(Pageable pageable, BookstoreUser user);
}
