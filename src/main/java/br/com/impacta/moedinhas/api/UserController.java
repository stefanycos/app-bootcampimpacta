package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.UserApplication;
import br.com.impacta.moedinhas.application.dto.request.UserRequest;
import br.com.impacta.moedinhas.application.dto.request.Views;
import br.com.impacta.moedinhas.application.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserApplication userApplication;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userApplication.create(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID userId, @Validated(Views.OnUpdate.class) @Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userApplication.update(userId, userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable UUID userId) {
        userApplication.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
