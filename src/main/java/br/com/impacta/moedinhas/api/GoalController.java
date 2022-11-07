package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.GoalApplication;
import br.com.impacta.moedinhas.application.dto.request.GoalRequest;
import br.com.impacta.moedinhas.application.dto.request.Views;
import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import br.com.impacta.moedinhas.application.dto.response.GoalResponse;
import br.com.impacta.moedinhas.application.dto.response.PageableResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/goals")
public class GoalController {

    private final GoalApplication goalApplication;

    @PostMapping
    public ResponseEntity<GoalResponse> save(@Validated(Views.OnCreate.class) @Valid @RequestBody GoalRequest request) {
        GoalResponse response = goalApplication.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated", response = PageableResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessageResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorMessageResponse.class)
    })
    @PatchMapping("/{goalId}")
    public ResponseEntity<GoalResponse> update(@PathVariable UUID goalId, @Validated(Views.OnUpdate.class) @Valid @RequestBody GoalRequest request) {
        GoalResponse response = goalApplication.update(goalId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<GoalResponse> find(@PathVariable UUID goalId) {
        GoalResponse response = goalApplication.findById(goalId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<PageableResponse> list(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "100") int size,
                                                 @RequestParam boolean reached) {
        PageableResponse goalResponse = goalApplication.list(page, size, reached);
        return ResponseEntity.status(HttpStatus.OK).body(goalResponse);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated", response = GoalResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessageResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorMessageResponse.class)
    })
    @PatchMapping("/{goalId}/approve")
    public ResponseEntity<GoalResponse> approve(@PathVariable UUID goalId) {
        GoalResponse goalResponse = goalApplication.approve(goalId);
        return ResponseEntity.status(HttpStatus.OK).body(goalResponse);
    }

}
