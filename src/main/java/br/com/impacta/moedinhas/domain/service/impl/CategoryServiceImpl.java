package br.com.impacta.moedinhas.domain.service.impl;

import br.com.impacta.moedinhas.domain.exception.ConflictException;
import br.com.impacta.moedinhas.domain.exception.InternalErrorException;
import br.com.impacta.moedinhas.domain.exception.NotFoundException;
import br.com.impacta.moedinhas.domain.model.Category;
import br.com.impacta.moedinhas.domain.service.CategoryService;
import br.com.impacta.moedinhas.domain.service.adapter.ObjectBeanAdapter;
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
        try {
            log.info("Saving category {} in database", category.getName());

            if (this.exists(category)) {
                throw new ConflictException(format("Category named %s already exists", category.getName()));
            }

            category.setCreatedAt(LocalDateTime.now());
            return categoryRepository.save(category);

        } catch (final Exception exception) {
            log.error("Error on trying to create category. Message: {}", exception.getMessage());
            throw new InternalErrorException(exception.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            log.info("Setting category status as inactive");
            Category category = this.findById(id);
            category.setStatus(false);
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);
        } catch (final Exception exception) {
            log.error("Error on trying to delete category. Message: {}", exception.getMessage());
            throw new InternalErrorException(exception.getMessage());
        }
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
        try {
            Category target = this.findById(id);
            ObjectBeanAdapter.copyNonNullProperties(source, target);
            target.setUpdatedAt(LocalDateTime.now());

            return target;
        } catch (final Exception exception) {
            log.error("Error on trying to update category. Message: {}", exception.getMessage());
            throw new InternalErrorException(exception.getMessage());
        }

    }
}
