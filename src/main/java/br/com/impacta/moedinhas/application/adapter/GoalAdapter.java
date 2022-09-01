package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.request.GoalRequest;
import br.com.impacta.moedinhas.application.dto.response.GoalResponse;
import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoalAdapter {

    public static GoalResponse toResponse(final Goal goal) {
        return GoalResponse.builder()
                .name(goal.getName())
                .id(goal.getId())
                .reached(goal.getReached())
                .description(goal.getDescription())
                .cost(goal.getCost())
                .build();
    }

    public static Goal toEntity(final GoalRequest request) {
        Goal goal = new Goal();
        goal.setName(request.getName());
        goal.setCost(request.getCost());
        goal.setDescription(request.getDescription());
        goal.setUser(User.builder().id(UUID.fromString(request.getUserId())).build());

        return goal;
    }

    public static List<GoalResponse> toResponseList(final List<Goal> categories) {
        return categories.stream()
                .map(GoalAdapter::toResponse)
                .collect(Collectors.toList());
    }
}