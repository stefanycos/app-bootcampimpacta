package br.com.impacta.moedinhas.infrastructure.repository;

import br.com.impacta.moedinhas.domain.model.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    Optional<Goal> findByNameIgnoreCase(String name);

    Page<Goal> findByReachedFalse(Pageable pageable);
}
