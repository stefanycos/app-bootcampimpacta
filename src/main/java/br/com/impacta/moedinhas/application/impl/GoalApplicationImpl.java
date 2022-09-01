package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.GoalApplication;
import br.com.impacta.moedinhas.application.adapter.GoalAdapter;
import br.com.impacta.moedinhas.application.dto.request.GoalRequest;
import br.com.impacta.moedinhas.application.dto.response.GoalResponse;
import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class GoalApplicationImpl implements GoalApplication {

    private final GoalService goalService;

    @Override
    public GoalResponse save(final GoalRequest request) {
        Goal goal = GoalAdapter.toEntity(request);
        goalService.save(goal);
        return GoalAdapter.toResponse(goal);
    }

    @Override
    public GoalResponse findById(final UUID id) {
        Goal goal = goalService.findById(id);
        return GoalAdapter.toResponse(goal);
    }

    @Override
    public List<GoalResponse> list() {
        return GoalAdapter.toResponseList(goalService.findAll());
    }

    @Override
    public List<GoalResponse> listNotReached() {
        return GoalAdapter.toResponseList(goalService.findAllNotReached());
    }

    @Override
    public GoalResponse update(UUID id, GoalRequest request) {
        Goal goal = GoalAdapter.toEntity(request);
        goalService.update(id, goal);
        return GoalAdapter.toResponse(goal);
    }

}
