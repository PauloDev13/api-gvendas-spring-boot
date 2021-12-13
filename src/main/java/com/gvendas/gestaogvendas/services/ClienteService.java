package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Categoria;
import com.gvendas.gestaogvendas.entities.Cliente;
import com.gvendas.gestaogvendas.exceptions.BusinessRulesException;
import com.gvendas.gestaogvendas.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    return clienteRepository.findById(codigo);
  }

  public Cliente save(Cliente cliente) {
    ValidateDuplicateClient(cliente);
    return clienteRepository.save(cliente);
  }

  private void ValidateDuplicateClient(Cliente cliente) {
    Cliente clienteFind = clienteRepository.findByNome(cliente.getNome());

    if(clienteFind != null && !Objects.equals(clienteFind.getId(), cliente.getId())) {
      throw new BusinessRulesException(String.format("O Cliente %s já existe", cliente.getNome().toUpperCase()));
    }
  }
}
