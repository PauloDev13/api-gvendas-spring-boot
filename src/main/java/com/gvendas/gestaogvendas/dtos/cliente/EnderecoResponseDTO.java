package com.gvendas.gestaogvendas.dtos.cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ApiModel("Endereço Response DTO")
public class EnderecoResponseDTO implements Serializable {

  @ApiModelProperty(value = "Logradouro")
  @NotBlank(message = "Logradouro")
  @Length(min = 5, max = 30, message = "Logradouro")
  private String logradouro;

  @ApiModelProperty(value = "Numero")
  @NotNull(message = "Numero")
  private Integer numero;

  @ApiModelProperty(value = "Complemento")
  private String complemento;

  @ApiModelProperty(value = "Bairro")
  @NotBlank(message = "Bairro")
  private String bairro;

  @ApiModelProperty(value = "Cep")
  @NotBlank(message = "Cep")
  private String cep;

  @ApiModelProperty(value = "Cidade")
  @NotBlank(message = "Cidade")
  private String cidade;

  @ApiModelProperty(value = "Estado")
  @NotBlank(message = "Estado")
  private String estado;
}
