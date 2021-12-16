package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Cliente;
import com.gvendas.gestaogvendas.exceptions.BusinessRulesException;
import com.gvendas.gestaogvendas.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {
  private final ClienteRepository clienteRepository;

  public List<Cliente> listAll() {
    return clienteRepository.findAll();
  }

  public Optional<Cliente> findByCodigo(Long codigo) {
    Optional<Cliente> clienteFound = clienteRepository.findById(codigo);

    if (clienteFound.isEmpty()) {
      throw new BusinessRulesException(String.format("Cliente com Código %s não cadastrado!", codigo));
    }
    return clienteFound;
  }

  public Cliente save(Cliente cliente) {
    validateDuplicateClient(cliente);
    return clienteRepository.save(cliente);
  }

  public Cliente update(Cliente cliente, Long codigo) {
    Cliente clienteUpdated = findByCodigo(codigo).get();
// Cliente clienteUpdated = validateClientExist(codigo);

    validateDuplicateClient(clienteUpdated);
    BeanUtils.copyProperties(cliente, clienteUpdated, "codigo");
    return clienteRepository.save(clienteUpdated);

  }

  public void delete(Long codigo) {
    clienteRepository.deleteById(codigo);
  }


//---  private Cliente validateClientExist(Long codigo) {
//    return clienteRepository.findById(codigo).get();

//    Optional<Cliente> cliente = clienteRepository.findById(codigo);
//
//    if (cliente.isEmpty()) {
//      throw new BusinessRulesException(String.format("Cliente com Código %s não existe", codigo));
//    }
//    return cliente.get();
//  }

  private void validateDuplicateClient(Cliente cliente) {
    Cliente clienteFind = clienteRepository.findByNome(cliente.getNome());

    if(clienteFind != null && !Objects.equals(clienteFind.getCodigo(), cliente.getCodigo())) {
      throw new BusinessRulesException(String.format("O Cliente %s já existe", cliente.getNome().toUpperCase()));
    }
  }
}
