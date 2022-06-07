package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.domain.model.Category;

public interface CategoryApplication {

    CategoryResponse save(CategoryRequest request);

    CategoryResponse findById(String id);
}
