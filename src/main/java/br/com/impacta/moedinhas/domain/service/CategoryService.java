package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category findById(UUID id);

    Category save(Category category);

    void delete(UUID id);

    boolean exists(Category category);

    List<Category> findAll();

    List<Category> findAllActive();

    Category update(UUID id, Category category);

}
