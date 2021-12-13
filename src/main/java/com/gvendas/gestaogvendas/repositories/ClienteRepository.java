package com.gvendas.gestaogvendas.repositories;

import com.gvendas.gestaogvendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Cliente findByNome(String nome);
}