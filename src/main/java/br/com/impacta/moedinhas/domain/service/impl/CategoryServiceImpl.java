package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.CategoryConflictException;
import br.com.impacta.moedinhas.domain.exception.CategoryNotFoundException;
import br.com.impacta.moedinhas.domain.model.Category;
import br.com.impacta.moedinhas.domain.service.CategoryService;
import br.com.impacta.moedinhas.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category Not Found"));
    }

    @Override
    public Category save(Category category) {
        if (this.exists(category)) {
            throw new CategoryConflictException("Category already exists");
        }

        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean exists(Category category) {
        return categoryRepository.findByNameIgnoreCase(category.getName()).isPresent();
    }
}
