package com.gvendas.gestaogvendas.dtos.cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ApiModel("Cliente Request DTO")
public class ClienteRequestDTO implements Serializable {

  @ApiModelProperty(value="Nome")
  @NotBlank(message = "Nome")
  @Length(min = 5, max = 50, message = "Nome")
  private String nome;

  @ApiModelProperty(value="Telefone")
  @NotBlank(message = "Telefone")
  private String telefone;

  @ApiModelProperty(value="Ativo")
  private Boolean ativo;

  @ApiModelProperty(value="Endereço")
  @NotNull(message = "Endereco")
  @Valid
  private EnderecoResponseDTO endereco;
}
