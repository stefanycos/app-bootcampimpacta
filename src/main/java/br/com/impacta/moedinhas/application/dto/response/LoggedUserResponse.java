package br.com.impacta.moedinhas.application.dto.response;

import br.com.impacta.moedinhas.domain.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class LoggedUserResponse {

    private UUID id;

    private String name;

    private String email;

    @JsonProperty("user_type")
    private Role role;

    private AccountResponse account;

    @ApiModelProperty
    @JsonProperty("user_parent")
    private LoggedUserResponse userParent;

}
