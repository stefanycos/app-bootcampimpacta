package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.AuthenticationApplication;
import br.com.impacta.moedinhas.application.dto.request.AuthenticationRequest;
import br.com.impacta.moedinhas.application.dto.response.AuthenticationResponse;
import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import br.com.impacta.moedinhas.application.dto.response.LoggedUserResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationApplication authenticationApplication;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User logged response", response = LoggedUserResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class)
    })
    @GetMapping("/current-user")
    public ResponseEntity<LoggedUserResponse> currentUser() {
        return ResponseEntity.ok(authenticationApplication.getLoggedUser());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Authenticated", response = AuthenticationResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class)
    })
    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationApplication.authenticate(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
