package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.GoalApplication;
import br.com.impacta.moedinhas.application.adapter.GoalAdapter;
import br.com.impacta.moedinhas.application.adapter.PageableAdapter;
import br.com.impacta.moedinhas.application.dto.request.GoalRequest;
import br.com.impacta.moedinhas.application.dto.response.GoalResponse;
import br.com.impacta.moedinhas.application.dto.response.PageableResponse;
import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PageableResponse list(int page, int size, boolean reached) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Goal> pageableGoals = goalService.findByReachedAndUserId(pageable, reached);

        List<GoalResponse> goalResponse = GoalAdapter.toResponseList(pageableGoals.getContent());
        return PageableAdapter.toPageable(goalResponse, pageableGoals);
    }

    @Override
    public GoalResponse update(UUID id, GoalRequest request) {
        Goal goal = GoalAdapter.toEntity(id, request);
        Goal updatedGoal = goalService.update(id, goal);
        return GoalAdapter.toResponse(updatedGoal);
    }

    @Override
    public GoalResponse approve(UUID id) {
        Goal goal = goalService.approve(id);
        return GoalAdapter.toResponse(goal);
    }

}
