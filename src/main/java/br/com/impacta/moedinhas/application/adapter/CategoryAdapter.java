package br.com.impacta.moedinhas.application.adapter;

import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.domain.model.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryAdapter {

    public static CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .id(category.getId())
                .status(category.getStatus() ? "ATIVO" : "INATIVO")
                .build();
    }

    public static Category toCollection(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        return category;
    }
}
