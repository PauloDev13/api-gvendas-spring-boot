package com.gvendas.gestaogvendas.repositories;

import com.gvendas.gestaogvendas.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
  List<Venda> findByClienteCodigo(Long codigoCliente);
}