package com.gvendas.gestaogvendas.repositories;

import com.gvendas.gestaogvendas.entities.ItemVenda;
import com.gvendas.gestaogvendas.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
  List<ItemVenda> findByVendaCodigo(Long codigoVenda);
}