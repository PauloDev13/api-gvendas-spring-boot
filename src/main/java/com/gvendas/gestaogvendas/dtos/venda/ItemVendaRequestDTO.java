package com.gvendas.gestaogvendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ApiModel("Itens da Venda Request DTO")
public class ItemVendaRequestDTO {

  @ApiModelProperty(value="Código Produto")
  private Long codigoProduto;

  @ApiModelProperty(value="Quantidade")
  private Integer quantidade;

  @ApiModelProperty(value="Preço Vendido")
  private BigDecimal precoVendido;
}
