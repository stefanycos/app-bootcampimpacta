package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.CategoryApplication;
import br.com.impacta.moedinhas.application.dto.request.CategoryRequest;
import br.com.impacta.moedinhas.application.dto.response.CategoryResponse;
import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryApplication categoryApplication;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse response = categoryApplication.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Updated", response = CategoryResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessageResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = ErrorMessageResponse.class)
    })
    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> update(@PathVariable UUID categoryId, @Valid @RequestBody CategoryRequest request) {
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

    @GetMapping("/active")
    public ResponseEntity<List<CategoryResponse>> listActive() {
        List<CategoryResponse> categoryResponses = categoryApplication.listActive();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponses);
    }

}
