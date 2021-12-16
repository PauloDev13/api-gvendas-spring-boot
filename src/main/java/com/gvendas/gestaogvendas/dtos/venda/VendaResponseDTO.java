package com.gvendas.gestaogvendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ApiModel("Venda Response DTO")
public class VendaResponseDTO implements Serializable {

  @ApiModelProperty(value="Código")
  private final Long codigo;

  @ApiModelProperty(value="Data")
  private final LocalDate data;

  @ApiModelProperty(value="Lista de itens da venda")
  private final List<ItemVendaResponseDTO> itemVendaResponseDTO;
}
