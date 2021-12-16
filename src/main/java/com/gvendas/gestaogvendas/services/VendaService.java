package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.dtos.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaogvendas.dtos.venda.ItemVendaRequestDTO;
import com.gvendas.gestaogvendas.dtos.venda.VendaRequestDTO;
import com.gvendas.gestaogvendas.dtos.venda.VendaResponseDTO;
import com.gvendas.gestaogvendas.entities.Cliente;
import com.gvendas.gestaogvendas.entities.ItemVenda;
import com.gvendas.gestaogvendas.entities.Produto;
import com.gvendas.gestaogvendas.entities.Venda;
import com.gvendas.gestaogvendas.exceptions.BusinessRulesException;
import com.gvendas.gestaogvendas.repositories.ItemVendaRepository;
import com.gvendas.gestaogvendas.repositories.ProdutoRepository;
import com.gvendas.gestaogvendas.repositories.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendaService extends AbstractVendaService {

  private final ClienteService clienteService;
  private final VendaRepository vendaRepository;
  private final ItemVendaRepository itemVendaRepository;
  private final ProdutoService produtoService;

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

  public ClienteVendaResponseDTO save(Long codigoCliente, VendaRequestDTO vendaDto) {
    Cliente cliente = validateClienteVendaExists(codigoCliente);
    validateProdutoExist(vendaDto.getItensVendaDto());
    Venda vendaSaved = saveVenda(cliente, vendaDto);
    return new ClienteVendaResponseDTO(vendaSaved.getCliente().getNome(), List.of(
        criaVendaResponseDTO(vendaSaved, itemVendaRepository.findByVendaCodigo(vendaSaved.getCodigo()))));
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

  private void validateProdutoExist(List<ItemVendaRequestDTO> itensVendaDto) {
    itensVendaDto.forEach(itemVenda -> produtoService.validateIfProductExist(itemVenda.getCodigoProduto()));
  }

  private Venda saveVenda(Cliente cliente, VendaRequestDTO vendaDto) {
    Venda vendaSaved = vendaRepository.save(new Venda(null, vendaDto.getData(), cliente));
    vendaDto.getItensVendaDto().stream().map(itemVendaDto -> criaItemVenda(itemVendaDto, vendaSaved))
        .forEach(itemVendaRepository::save);
    return vendaSaved;
  }

  private ItemVenda criaItemVenda(ItemVendaRequestDTO itemVendaDto, Venda venda) {
    return new ItemVenda(
        null, itemVendaDto.getQuantidade(), itemVendaDto.getPrecoVendido(),
        new Produto(itemVendaDto.getCodigoProduto()), venda);
  }
}
