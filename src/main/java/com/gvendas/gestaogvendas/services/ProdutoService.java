package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Produto;
import com.gvendas.gestaogvendas.exceptions.BusinessRulesException;
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
  private final CategoriaService categoriaService;

  public ProdutoService(ProdutoRepository produtoRepository, CategoriaService categoriaService) {
    this.produtoRepository = produtoRepository;
    this.categoriaService = categoriaService;
  }

  public List<Produto> listProductsByCategory(Long codigo) {
    return produtoRepository.findByCategoriaCodigo(codigo);
  }

  public Optional<Produto> findProductById(Long codigo, Long codigoCategoria) {
    return produtoRepository.findByCodigo(codigo, codigoCategoria);
  }

  public Produto save(Produto produto) {
    validateIfCategoryExists(produto.getCategoria().getCodigo());
    validateDuplicateProduct(produto);
    return produtoRepository.save(produto);
  }

  public Produto update(Long codigoCategoria, Long codigoProduto, Produto produto) {
    Produto produtoSaved = validateIfProductExist(codigoProduto, codigoCategoria);
    validateIfCategoryExists(codigoCategoria);
    validateDuplicateProduct(produto);
    BeanUtils.copyProperties(produto, produtoSaved, "codigo");
    return produtoRepository.save(produtoSaved);
  }

  private Produto validateIfProductExist(Long codigoProduto, Long codigoCategoria) {
    Optional<Produto> produto = findProductById(codigoProduto, codigoCategoria);
    if(produto.isEmpty()) {
      throw new EmptyResultDataAccessException(1);
    }
    return produto.get();
  }

  //
//  public void delete(Long codigo) {
//    categoriaRepository.deleteById(codigo);
//  }
//
  private void validateIfCategoryExists(Long codigoCategoria) {
    if (codigoCategoria == null) {
      throw new BusinessRulesException("Informe uma Categoria");
    }

    if (categoriaService.findById(codigoCategoria).isEmpty()) {
      throw new BusinessRulesException(String.format("A Categoria com código %s não existe no cadastro",
          codigoCategoria));
    }
  }

  private void validateDuplicateProduct(Produto produto) {
    Optional<Produto> produtoFind = produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(),
        produto.getDescricao());
    if(produtoFind.isPresent() && !Objects.equals(produtoFind.get().getCodigo(), produto.getCodigo())) {
      throw new BusinessRulesException(String.format("O Produto %s já existe no cadastro", produto.getDescricao().toUpperCase()));
    }
  }
}
