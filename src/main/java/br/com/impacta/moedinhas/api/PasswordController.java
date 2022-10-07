package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.PasswordApplication;
import br.com.impacta.moedinhas.application.dto.request.PasswordRequest;
import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {

    private final PasswordApplication passwordApplication;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Token successfully generated"),
            @ApiResponse(code = 500, message = "Internal error on sending email", response = ErrorMessageResponse.class)
    })
    @PatchMapping("/token/{email}")
    public ResponseEntity<Object> sendTokenEmail(@PathVariable String email) {
        passwordApplication.sendResetPasswordLinkEmail(email);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Token successfully generated")
    })
    @PatchMapping("/reset/{token}")
    public ResponseEntity<Object> changePassword(@RequestBody PasswordRequest passwordRequest, @PathVariable String token) {
        passwordApplication.changePassword(token, passwordRequest.getPassword());
        return ResponseEntity.ok().build();
    }

}
