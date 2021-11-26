package com.gvendas.gestaogvendas.controllers;

import com.gvendas.gestaogvendas.entities.Produto;
import com.gvendas.gestaogvendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria/{codigoCategoria}/produtos")
@Api(tags = "Produto")
public class ProdutoController {
  private final ProdutoService produtoService;

  public ProdutoController(ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

  @ApiOperation(
      value = "Lista todos os registros de produtos pelo código da sua Categoria",
      nickname = "todosProdutos "
  )
  @GetMapping
  public List<Produto> listProductsByCategory(@PathVariable Long codigoCategoria) {
    return produtoService.listProductsByCategory(codigoCategoria);
  }

  @ApiOperation(
      value = "Busca um único registro de Produto pelo seu código e o código da sua Categoria",
      nickname = "produtoPorCodigo"
  )
  @GetMapping("/{codigoProduto}")
  public ResponseEntity<Optional<Produto>> findById(
      @PathVariable Long codigoProduto, @PathVariable Long codigoCategoria
  ) {
    Optional<Produto> produto = produtoService.findProductById(codigoProduto, codigoCategoria);
    return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
  }

  @ApiOperation(value = "Insere um registro de produto", nickname="salvaProduto")
  @PostMapping
  public ResponseEntity<Produto> save(
      @PathVariable Long codigoCategoria, @Valid @RequestBody() Produto produto
      ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(codigoCategoria, produto));
  }

  @ApiOperation(
      value = "Atualiza um único registro de Produto fornecido seu código e o código da sua Categoria",
      nickname = "atualizarCategoria"
  )
  @PutMapping("/{codigoProduto}")
  public ResponseEntity<Produto> update(
      @PathVariable Long codigoCategoria,
      @PathVariable Long codigoProduto,
      @Valid @RequestBody Produto produto
  ) {
    return ResponseEntity.ok(produtoService.update(codigoCategoria, codigoProduto, produto));
  }

  @ApiOperation(value = "Exclui um único registro de Produto fornecido seu código", nickname = "excluiProduto")
  @DeleteMapping("/{codigoProduto}")
  public ResponseEntity<Void> delete(@PathVariable Long codigoProduto, @PathVariable Long codigoCategoria) {
    produtoService.delete(codigoProduto, codigoCategoria);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
