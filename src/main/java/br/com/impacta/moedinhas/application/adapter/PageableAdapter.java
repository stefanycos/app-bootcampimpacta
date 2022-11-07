package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.response.PageableInfoResponse;
import br.com.impacta.moedinhas.application.dto.response.PageableResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableAdapter {

    public static <T, S> PageableResponse toPageable(List<T> data, Page<S> page) {
        PageableInfoResponse pageableInfoResponse = PageableInfoResponse.builder()
                .currentPage(page.getNumber())
                .totalItems(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();

        return new PageableResponse<>(data, pageableInfoResponse);
    }
}
