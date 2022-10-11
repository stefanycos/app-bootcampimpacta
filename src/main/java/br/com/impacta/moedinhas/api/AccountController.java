package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.AccountApplication;
import br.com.impacta.moedinhas.application.dto.request.AccountRequest;
import br.com.impacta.moedinhas.application.dto.response.AccountResponse;
import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountApplication accountApplication;

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "User account"),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessageResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorMessageResponse.class)
    })
    @PutMapping("/{accountId}")
    public ResponseEntity<Object> deposit(@PathVariable UUID accountId, @Valid @RequestBody AccountRequest request) {
        accountApplication.deposit(request.getAmount(), accountId);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User account", response = AccountResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorMessageResponse.class)
    })
    @GetMapping("/{userId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable UUID userId) {
        AccountResponse accountResponse = accountApplication.getAccount(userId);
        return ResponseEntity.ok(accountResponse);
    }
}
