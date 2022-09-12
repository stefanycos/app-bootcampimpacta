package br.com.impacta.moedinhas.infrastructure.repository;

import br.com.impacta.moedinhas.domain.model.Goal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalRepository extends CrudRepository<Goal, UUID> {

    Optional<Goal> findByNameIgnoreCase(String name);

    List<Goal> findByReachedFalse();
}
