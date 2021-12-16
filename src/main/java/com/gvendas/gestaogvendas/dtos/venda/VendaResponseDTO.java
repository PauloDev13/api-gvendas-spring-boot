package com.gvendas.gestaogvendas.dtos.venda;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class VendaResponseDTO implements Serializable {
  private final Long codigo;
  private final LocalDate data;
  private final List<ItemVendaResponseDTO> itemVendaResponseDTO;
}
