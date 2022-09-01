package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.GoalApplication;
import br.com.impacta.moedinhas.application.dto.request.GoalRequest;
import br.com.impacta.moedinhas.application.dto.response.GoalResponse;
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
@RequestMapping("/api/v1/goals")
public class GoalController {

    private final GoalApplication goalApplication;

    @PostMapping
    public ResponseEntity<GoalResponse> save(@Valid @RequestBody GoalRequest request) {
        GoalResponse response = goalApplication.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Updated", response = GoalResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessageResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = ErrorMessageResponse.class)
    })
    @PatchMapping("/{goalId}")
    public ResponseEntity<GoalResponse> update(@PathVariable UUID goalId, @Valid @RequestBody GoalRequest request) {
        GoalResponse response = goalApplication.update(goalId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<GoalResponse> find(@PathVariable UUID goalId) {
        GoalResponse response = goalApplication.findById(goalId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GoalResponse>> list() {
        List<GoalResponse> goalResponse = goalApplication.list();
        return ResponseEntity.status(HttpStatus.OK).body(goalResponse);
    }

    @GetMapping("/not_reached")
    public ResponseEntity<List<GoalResponse>> listNotReached() {
        List<GoalResponse> goalResponse = goalApplication.listNotReached();
        return ResponseEntity.status(HttpStatus.OK).body(goalResponse);
    }

}
