package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.response.TransactionResponse;
import br.com.impacta.moedinhas.application.dto.response.TransactionTypeResponse;
import br.com.impacta.moedinhas.domain.model.Transaction;
import br.com.impacta.moedinhas.domain.model.enums.TransactionType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionAdapter {

    public static TransactionResponse toResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .amount(transaction.getAmount())
                .date(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(transaction.getCreatedAt()))
                .goal(transaction.getType().equals(TransactionType.WITHDRAW) ? transaction.getGoal().getDescription() : null)
                .type(transaction.getType().equals(TransactionType.WITHDRAW) ? TransactionTypeResponse.OUTPUT : TransactionTypeResponse.INPUT)
                .build();
    }

    public static List<TransactionResponse> toResponseList(final List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
