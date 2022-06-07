package br.com.impacta.moedinhas.domain.service;

import br.com.impacta.moedinhas.domain.model.Category;

public interface CategoryService {

    Category findById(String id);

    Category save(Category category);

    void delete(String id);

    boolean exists(Category category);

}
