package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.response.PageableResponse;

import java.util.UUID;

public interface TransactionApplication {

    PageableResponse list(int page, int size, UUID accountId);

}
