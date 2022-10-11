package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.TransactionApplication;
import br.com.impacta.moedinhas.application.adapter.PageableAdapter;
import br.com.impacta.moedinhas.application.adapter.TransactionAdapter;
import br.com.impacta.moedinhas.application.dto.response.PageableResponse;
import br.com.impacta.moedinhas.application.dto.response.TransactionResponse;
import br.com.impacta.moedinhas.domain.model.Transaction;
import br.com.impacta.moedinhas.domain.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class TransactionApplicationImpl implements TransactionApplication {

    private final TransactionService transactionService;

    @Override
    public PageableResponse list(int page, int size, UUID accountId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> pageableTransaction = transactionService.findTransactionsByAccount(pageable, accountId);

        List<TransactionResponse> transactionResponse = TransactionAdapter.toResponseList(pageableTransaction.getContent());
        return PageableAdapter.toPageable(transactionResponse, pageableTransaction);
    }
}
