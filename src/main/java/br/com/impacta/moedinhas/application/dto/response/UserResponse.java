package br.com.impacta.moedinhas.application.dto.response;

import br.com.impacta.moedinhas.domain.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Setter
@Getter
public class UserResponse {

    private UUID id;

    private String name;

    private String email;

    @ApiModelProperty
    @JsonProperty("user_type")
    private Role role;

    private String birthday;

}
