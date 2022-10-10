package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.model.Transaction;
import br.com.impacta.moedinhas.domain.service.TransactionService;
import br.com.impacta.moedinhas.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;


    @Override
    public Page<Transaction> findTransactionsByAccount(Pageable pageable, UUID accountId) {
        return transactionRepository.findTransactionsByAccount(pageable, accountId);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
