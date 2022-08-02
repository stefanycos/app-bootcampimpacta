package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.dto.request.AuthenticationRequest;
import br.com.impacta.moedinhas.application.dto.response.AuthenticationResponse;
import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import br.com.impacta.moedinhas.configuration.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authManager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken auth = authenticationRequest.converter();
        try {
            Authentication authentication = authManager.authenticate(auth);

            String token = tokenService.createToken(authentication);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(token));

        } catch (AuthenticationException e) {
            log.error("Error on trying to authenticate user. Error {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessageResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .message("Unauthorized")
                    .description("Bad Credentials").build());
        }
    }
}
