package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GoalService {

    Goal findById(UUID id);

    Goal save(Goal goal);

    boolean exists(Goal goal, User user);

    Page<Goal> findByReachedAndUserId(Pageable pageable, Boolean reached);

    Goal update(UUID id, Goal goal);

    Goal approve(UUID id);

}
