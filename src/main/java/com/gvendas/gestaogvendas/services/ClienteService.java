package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Cliente;
import com.gvendas.gestaogvendas.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
