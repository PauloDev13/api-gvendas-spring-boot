package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Produto;
import com.gvendas.gestaogvendas.exceptions.BusinessRulesException;
import com.gvendas.gestaogvendas.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    if(produtoRepository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(),
        produto.getDescricao()).isPresent()
    ) {
      throw new BusinessRulesException(String.format("O Produto %s já existe no cadastro", produto.getDescricao().toUpperCase()));
    }
  }
}
