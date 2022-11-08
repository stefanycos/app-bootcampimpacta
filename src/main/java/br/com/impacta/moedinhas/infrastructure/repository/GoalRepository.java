package br.com.impacta.moedinhas.infrastructure.repository;

import br.com.impacta.moedinhas.domain.model.Goal;
import br.com.impacta.moedinhas.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalRepository extends JpaRepository<Goal, UUID> {

    Optional<Goal> findByNameIgnoreCaseAndUser(String name, User user);

    @Query(value = "select * from goals g where g.reached = :reached and g.user_id = :id", nativeQuery = true)
    Page<Goal> findByReachedAndUserId(Pageable pageable, @Param("reached") Boolean reached, @Param("id") UUID id);
}
