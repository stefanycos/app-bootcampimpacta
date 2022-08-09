package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.Category;
import br.com.impacta.moedinhas.domain.service.CategoryService;
import br.com.impacta.moedinhas.infrastructure.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findById(UUID id) {
        log.info("Searching category with id {}", id);
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category Not Found"));
    }

    @Override
    public Category save(Category category) {
        log.info("Saving category {} in database", category.getName());

        if (this.exists(category)) {
            throw new ConflictException(format("Category named %s already exists", category.getName()));
        }

        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(UUID id) {
        log.info("Setting category status as inactive");
        Category category = this.findById(id);
        category.setStatus(false);
        category.setUpdatedAt(LocalDateTime.now());
        categoryRepository.save(category);
    }

    @Override
    public boolean exists(Category category) {
        return categoryRepository.findByNameIgnoreCase(category.getName()).isPresent();
    }

    @Override
    public List<Category> findAll() {
        Iterable<Category> categories = categoryRepository.findAll();
        return StreamSupport.stream(categories.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> findAllActive() {
        return categoryRepository.findByStatusTrue();
    }

    @Override
    public Category update(UUID id, Category source) {
        Category target = this.findById(id);
        target.setUpdatedAt(LocalDateTime.now());
        target.setName(source.getName());
        categoryRepository.save(target);
        target.setId(id);
        return target;
    }
}
