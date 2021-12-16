package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.dtos.venda.ClienteVendaResponseDTO;
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
public class VendaService extends AbstractVendaService {

  private final ClienteService clienteService;
  private final VendaRepository vendaRepository;
  private final ItemVendaRepository itemVendaRepository;

  public ClienteVendaResponseDTO ListVendaByCliente(Long codigoCliente) {
    Cliente clienteValidate = validateClienteVendaExists(codigoCliente);
    List<VendaResponseDTO> vendaResponseDtoList = vendaRepository.findByClienteCodigo(codigoCliente).stream()
            .map(venda -> criaVendaResponseDTO(venda, itemVendaRepository.findByVendaCodigo(venda.getCodigo())))
            .collect(Collectors.toList());

    return new ClienteVendaResponseDTO(clienteValidate.getNome(), vendaResponseDtoList);
  }

  public ClienteVendaResponseDTO listVendaByCodigo(Long codigoVenda) {
    Venda vendaExist = validateVendaExist(codigoVenda);
    List<ItemVenda> itensVendaList = itemVendaRepository.findByVendaCodigo(codigoVenda);

    return new ClienteVendaResponseDTO(vendaExist.getCliente().getNome(),
        List.of(criaVendaResponseDTO(vendaExist,itensVendaList)));
  }

  //-- Métodos auxiliares
  private Venda validateVendaExist(Long codigoVenda) {
    Optional<Venda> venda = vendaRepository.findById(codigoVenda);

    if (venda.isEmpty()) {
      throw new BusinessRulesException(String.format("Venda com Código %s não existe!", codigoVenda));
    }
    return venda.get();
  }

  private Cliente validateClienteVendaExists(Long codigoCliente) {
    Optional<Cliente> cliente = clienteService.findByCodigo(codigoCliente);
    return cliente.orElse(null);
  }
}
