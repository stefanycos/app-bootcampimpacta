package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Goal;

import java.util.List;
import java.util.UUID;

public interface GoalService {

    Goal findById(UUID id);

    Goal save(Goal goal);

    boolean exists(Goal goal);

    List<Goal> findAll();

    List<Goal> findAllNotReached();

    Goal update(UUID id, Goal goal);

}
