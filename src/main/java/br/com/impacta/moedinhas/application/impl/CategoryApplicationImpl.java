package br.com.impacta.moedinhas.application.impl;

import br.com.impacta.moedinhas.application.CategoryApplication;
import br.com.impacta.moedinhas.application.adapter.CategoryAdapter;
import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.domain.model.Category;
import br.com.impacta.moedinhas.domain.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class CategoryApplicationImpl implements CategoryApplication {

    private final CategoryService categoryService;

    @Override
    public CategoryResponse save(final CategoryRequest request) {
        Category category = CategoryAdapter.toEntity(request);
        categoryService.save(category);
        return CategoryAdapter.toResponse(category);
    }

    @Override
    public CategoryResponse findById(final UUID id) {
        Category category = categoryService.findById(id);
        return CategoryAdapter.toResponse(category);
    }

    @Override
    public List<CategoryResponse> list() {
        return CategoryAdapter.toResponseList(categoryService.findAll());
    }

    @Override
    public List<CategoryResponse> listActive() {
        return CategoryAdapter.toResponseList(categoryService.findAllActive());
    }

    @Override
    public CategoryResponse update(UUID id, CategoryRequest request) {
        Category category = CategoryAdapter.toEntity(request);
        categoryService.update(id, category);
        return CategoryAdapter.toResponse(category);
    }

    @Override
    public void delete(UUID id) {
        categoryService.delete(id);
    }
}
