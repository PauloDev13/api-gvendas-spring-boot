package com.gvendas.gestaogvendas.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel("Endereço Response DTO")
public class EnderecoResponseDTO implements Serializable {

  @ApiModelProperty(value = "Logradouro")
  private String logradouro;

  @ApiModelProperty(value = "Numero")
  private Integer numero;

  @ApiModelProperty(value = "Complemento")
  private String complemento;

  @ApiModelProperty(value = "Bairro")
  private String bairro;

  @ApiModelProperty(value = "Cep")
  private String cep;

  @ApiModelProperty(value = "Cidade")
  private String cidade;

  @ApiModelProperty(value = "Estado")
  private String estado;
}
