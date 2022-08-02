package br.com.impacta.moedinhas.application.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@Data
public class UserRequest {

    private String name;

    private String password;

    private String email;

}
