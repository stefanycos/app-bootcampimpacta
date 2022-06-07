package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.CategoryApplication;
import br.com.impacta.moedinhas.application.adapter.CategoryAdapter;
import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.domain.model.Category;
import br.com.impacta.moedinhas.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryApplicationImpl implements CategoryApplication {

    private final CategoryService categoryService;

    @Override
    public CategoryResponse save(final CategoryRequest request) {
        Category category = CategoryAdapter.toCollection(request);
        categoryService.save(category);
        return CategoryAdapter.toResponse(category);
    }

    @Override
    public CategoryResponse findById(final String id) {
        Category category = categoryService.findById(id);
        return CategoryAdapter.toResponse(category);
    }
}
