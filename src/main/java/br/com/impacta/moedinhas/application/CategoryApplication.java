package br.com.impacta.moedinhas.application;

import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.domain.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryApplication {

    CategoryResponse save(CategoryRequest request);

    CategoryResponse findById(UUID id);

    List<CategoryResponse> list();

    List<CategoryResponse> listActive();

    CategoryResponse update(UUID id, CategoryRequest request);

}
