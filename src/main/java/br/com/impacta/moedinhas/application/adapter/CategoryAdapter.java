package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.domain.model.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryAdapter {

    public static CategoryResponse toResponse(final Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .id(category.getId())
                .status(category.getStatus() ? "ATIVO" : "INATIVO")
                .build();
    }

    public static Category toEntity(final CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        return category;
    }

    public static List<CategoryResponse> toResponseList(final List<Category> categories) {
        return categories.stream()
                .map(CategoryAdapter::toResponse)
                .collect(Collectors.toList());
    }
}
