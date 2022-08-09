package br.com.impacta.moedinhas.infrastructure.repository;

import br.com.impacta.moedinhas.domain.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<Category, UUID> {

    Optional<Category> findByNameIgnoreCase(String name);

    List<Category> findByStatusTrue();
}
