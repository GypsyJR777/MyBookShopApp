package com.github.gypsyjr777.entity.payments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TransactionCount {
    private int count;
    private List<BalanceTransactionEntity> userTransactions;


    public TransactionCount(List<BalanceTransactionEntity> userTransactions) {
        this.count = userTransactions.size();
        this.userTransactions = userTransactions;
    }
}
