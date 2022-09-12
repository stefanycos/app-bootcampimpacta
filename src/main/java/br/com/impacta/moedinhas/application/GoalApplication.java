package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.request.GoalRequest;
import br.com.impacta.moedinhas.application.dto.response.GoalResponse;

import java.util.List;
import java.util.UUID;

public interface GoalApplication {

    GoalResponse save(GoalRequest request);

    GoalResponse findById(UUID id);

    List<GoalResponse> list();

    List<GoalResponse> listNotReached();

    GoalResponse update(UUID id, GoalRequest request);

}
