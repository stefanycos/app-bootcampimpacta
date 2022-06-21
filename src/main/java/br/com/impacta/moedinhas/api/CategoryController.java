package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.CategoryApplication;
import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryApplication categoryApplication;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request) {
        CategoryResponse response = categoryApplication.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID categoryId, @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryApplication.update(categoryId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> find(@PathVariable UUID categoryId) {
        CategoryResponse response = categoryApplication.findById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list() {
        List<CategoryResponse> categoryResponses = categoryApplication.list();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponses);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> remove(@PathVariable UUID categoryId) {
        categoryApplication.delete(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
