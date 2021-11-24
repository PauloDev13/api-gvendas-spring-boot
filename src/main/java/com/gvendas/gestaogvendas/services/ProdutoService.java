package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Produto;
import com.gvendas.gestaogvendas.exceptions.DuplicateCategoryException;
import com.gvendas.gestaogvendas.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {
  private final ProdutoRepository produtoRepository;

  public ProdutoService(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  public List<Produto> listProductsByCategory(Long codigo) {
    return produtoRepository.findByCategoriaCodigo(codigo);
  }

  public Optional<Produto> findProductById(Long codigo, Long codigoCategoria) {
    return produtoRepository.findByCodigo(codigo, codigoCategoria);
  }

//  public Produto save(Produto categoria) {
//    ValidateDuplicateCategory(categoria);
//    return categoriaRepository.save(categoria);
//  }
//
//  public Produto update(Long codigo, Produto categoria) {
//    Produto categoriaSaved = validateCategory(codigo);
//    ValidateDuplicateCategory(categoria);
//    BeanUtils.copyProperties(categoria, categoriaSaved, "codigo");
//    return categoriaRepository.save(categoriaSaved);
//  }
//
//  public void delete(Long codigo) {
//    categoriaRepository.deleteById(codigo);
//  }
//
//  private Produto validateCategory(Long id) {
//    Optional<Produto> categoria = findById(id);
//
//    if (categoria.isEmpty()) {
//      throw new EmptyResultDataAccessException(1);
//    }
//    return categoria.get();
//  }
//
//  private void ValidateDuplicateCategory(Produto categoria) {
//    Produto categoriaFind = categoriaRepository.findByNome(categoria.getNome());
//
//    if(categoriaFind != null && !Objects.equals(categoriaFind.getCodigo(), categoria.getCodigo())) {
//      throw new DuplicateCategoryException(String.format("A categoria %s já existe", categoria.getNome().toUpperCase()));
//    }
//  }
}
