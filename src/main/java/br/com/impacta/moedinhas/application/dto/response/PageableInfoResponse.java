package br.com.impacta.moedinhas.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PageableInfoResponse {

    @JsonProperty("current")
    private int currentPage;

    private long totalItems;

    private int totalPages;
}
