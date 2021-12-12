package com.gvendas.gestaogvendas.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel("Cliente Response DTO")
public class ClienteResponseDTO implements Serializable {

  @ApiModelProperty(value = "Código")
  private Long id;

  @ApiModelProperty(value = "Nome")
  private String nome;

  @ApiModelProperty(value = "Telefone")
  private String telefone;

  @ApiModelProperty(value = "Ativo")
  private Boolean ativo;

  @ApiModelProperty(value = "Endereco")
  private EnderecoResponseDTO endereco;

}
