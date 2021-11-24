package com.gvendas.gestaogvendas.repositories;

import com.gvendas.gestaogvendas.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
  List<Produto> findByCategoriaCodigo(Long codigoCategoria);
//  Produto findByCodigoAndCategoria_Codigo(Long codigo, Long codigo_categoria);

  @Query("SELECT p FROM Produto p WHERE p.codigo = :codigo AND p.categoria.codigo = :codigoCategoria")
  Optional<Produto> findByCodigo(Long codigo, Long codigoCategoria);

  Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);
}