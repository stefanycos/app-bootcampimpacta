package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionService {

    Page<Transaction> findTransactionsByAccount(Pageable pageable, UUID accountId);

    void save(Transaction transaction);
}
