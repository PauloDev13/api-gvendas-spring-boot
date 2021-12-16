package com.gvendas.gestaogvendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel("Cliente Venda Response DTO")
public class ClienteVendaResponseDTO implements Serializable {

  @ApiModelProperty(value="Nome")
  private String nome;

  @ApiModelProperty(value="Venda")
  private List<VendaResponseDTO> vendaResponseDTO;
}
