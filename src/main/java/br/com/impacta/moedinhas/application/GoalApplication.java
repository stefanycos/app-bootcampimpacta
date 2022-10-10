package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.request.GoalRequest;
import br.com.impacta.moedinhas.application.dto.response.GoalResponse;
import br.com.impacta.moedinhas.application.dto.response.PageableResponse;

import java.util.UUID;

public interface GoalApplication {

    GoalResponse save(GoalRequest request);

    GoalResponse findById(UUID id);

    PageableResponse list(int page, int size, boolean reached);

    GoalResponse update(UUID id, GoalRequest request);

    GoalResponse approve(UUID id);

}
