package com.gvendas.gestaogvendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel("Item Venda Response DTO")
public class ItemVendaResponseDTO implements Serializable {

  @ApiModelProperty(value="Código")
  private Long codigo;

  @ApiModelProperty(value="Quantidade")
  private Integer quantidade;

  @ApiModelProperty(value="Preço Vendido")
  private BigDecimal precoVendido;

  @ApiModelProperty(value="Código Produto")
  private Long codigoProduto;

  @ApiModelProperty(value="Descrição Produto")
  private String produtoDescricao;
}
