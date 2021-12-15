package com.gvendas.gestaogvendas.dto;

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
  private Long codigo;

  @ApiModelProperty(value="Data")
  private LocalDate data;

  @ApiModelProperty(value="Itens da Venda")
  private List<ItemVendaResponseDTO> itemVendaResponseDto;
}
