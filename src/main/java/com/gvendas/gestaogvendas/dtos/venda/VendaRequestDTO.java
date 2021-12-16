package com.gvendas.gestaogvendas.dtos.venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ApiModel("Venda Request DTO")
public class VendaRequestDTO {

  @ApiModelProperty(value="Data")
  private LocalDate data;

  @ApiModelProperty(value="Itens da venda")
  private List<ItemVendaRequestDTO> itensVendaDto;
}
