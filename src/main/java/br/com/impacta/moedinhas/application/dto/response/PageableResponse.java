package br.com.impacta.moedinhas.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class PageableResponse<T> {

    private List<T> data;

    private PageableInfoResponse pageable;

}
