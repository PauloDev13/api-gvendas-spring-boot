package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.dtos.venda.ItemVendaResponseDTO;
import com.gvendas.gestaogvendas.dtos.venda.VendaResponseDTO;
import com.gvendas.gestaogvendas.entities.ItemVenda;
import com.gvendas.gestaogvendas.entities.Venda;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVendaService {
  protected VendaResponseDTO criaVendaResponseDTO(Venda venda, List<ItemVenda> itensVendaList) {
    List<ItemVendaResponseDTO> itemVendaResponseDTO = itensVendaList.stream()
        .map(this::criaItemVendaResponseDTO).collect(Collectors.toList());
    return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itemVendaResponseDTO);
  }

  protected ItemVendaResponseDTO criaItemVendaResponseDTO(ItemVenda itemVenda) {
    return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
        itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
  }
}
