package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.dtos.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaogvendas.dtos.venda.ItemVendaResponseDTO;
import com.gvendas.gestaogvendas.dtos.venda.VendaResponseDTO;
import com.gvendas.gestaogvendas.entities.Cliente;
import com.gvendas.gestaogvendas.entities.ItemVenda;
import com.gvendas.gestaogvendas.entities.Venda;
import com.gvendas.gestaogvendas.exceptions.BusinessRulesException;
import com.gvendas.gestaogvendas.repositories.ItemVendaRepository;
import com.gvendas.gestaogvendas.repositories.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaService {

  private final ClienteService clienteService;
  private final VendaRepository vendaRepository;
  private final ItemVendaRepository itemVendaRepository;

  public ClienteVendaResponseDTO ListVendaByCliente(Long codigoCliente) {
    Cliente clienteValidate = validateClienteVendaExists(codigoCliente);
    List<VendaResponseDTO> vendaResponseDtoList =
        vendaRepository.findByClienteCodigo(codigoCliente).stream().map(this::criaVendaResponseDTO)
        .collect(Collectors.toList());
    return new ClienteVendaResponseDTO(clienteValidate.getNome(), vendaResponseDtoList);
  }

  public ClienteVendaResponseDTO listVendaByCodigo(Long codigoVenda) {
    Venda vendaExist = validateVendaExist(codigoVenda);
    return new ClienteVendaResponseDTO(vendaExist.getCliente().getNome(), List.of(criaVendaResponseDTO(vendaExist)));
  }

  private Venda validateVendaExist(Long codigoVenda) {
    Optional<Venda> venda = vendaRepository.findById(codigoVenda);

    if (venda.isEmpty()) {
      throw new BusinessRulesException(String.format("Venda com Código %s não existe!", codigoVenda));
    }
    return venda.get();
  }

  //-- Métodos auxiliares
  private Cliente validateClienteVendaExists(Long codigoCliente) {
    Optional<Cliente> cliente = clienteService.findByCodigo(codigoCliente);
    return cliente.orElse(null);
  }

  private VendaResponseDTO criaVendaResponseDTO(Venda venda) {
    List<ItemVendaResponseDTO> itemVendaResponseDTO = itemVendaRepository.findByVendaCodigo(venda.getCodigo())
        .stream().map(this::criaItemVendaResponseDTO).collect(Collectors.toList());
    return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itemVendaResponseDTO);
  }

  private ItemVendaResponseDTO criaItemVendaResponseDTO(ItemVenda itemVenda) {
    return new ItemVendaResponseDTO(itemVenda.getCodigo(), itemVenda.getQuantidade(), itemVenda.getPrecoVendido(),
        itemVenda.getProduto().getCodigo(), itemVenda.getProduto().getDescricao());
  }
}
