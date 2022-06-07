package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.CategoryApplication;
import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.server.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> find(@PathVariable String categoryId) {
        CategoryResponse response = categoryApplication.findById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
