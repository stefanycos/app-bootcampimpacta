package br.com.impacta.moedinhas.api;

import br.com.impacta.moedinhas.application.TransactionApplication;
import br.com.impacta.moedinhas.application.dto.response.ErrorMessageResponse;
import br.com.impacta.moedinhas.application.dto.response.PageableResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionsController {

    private final TransactionApplication transactionApplication;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pageable list of TransactionResponse.class", response = PageableResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorMessageResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessageResponse.class),
            @ApiResponse(code = 404, message = "Account not found", response = ErrorMessageResponse.class)
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<PageableResponse> listUserTransactions(@PathVariable UUID accountId, @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "100") int size) {
        PageableResponse pageableResponse = transactionApplication.list(page, size, accountId);
        return ResponseEntity.ok(pageableResponse);
    }

}
