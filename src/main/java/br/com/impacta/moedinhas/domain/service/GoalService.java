package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GoalService {

    Goal findById(UUID id);

    Goal save(Goal goal);

    boolean exists(Goal goal);

    Page<Goal> findAll(Pageable pageable);

    Page<Goal> findAllNotReached(Pageable pageable);

    Goal update(UUID id, Goal goal);

}
