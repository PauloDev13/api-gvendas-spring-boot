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
      nickname = "ListarTodos "
  )
  @GetMapping
  public List<Produto> listProductsByCategory(@PathVariable Long codigoCategoria) {
    return produtoService.listProductsByCategory(codigoCategoria);
  }

  @ApiOperation(
      value = "Busca um único registro de produto pelo seu código e o código da sua Categoria",
      nickname = "BuscarPorCodigo"
  )
  @GetMapping("/{codigo}")
  public ResponseEntity<Optional<Produto>> findById(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
    Optional<Produto> produto = produtoService.findProductById(codigo, codigoCategoria);
    return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
  }

//  @ApiOperation(value = "Insere um registro de categoria")
//  @PostMapping
//  public ResponseEntity<Categoria> save(@Valid @RequestBody() Categoria categoria) {
//    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
//  }
//
//  @ApiOperation(value = "Atualiza um único registro de categoria fornecido seu código")
//  @PutMapping("/{codigo}")
//  public ResponseEntity<Categoria> update(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
//    return ResponseEntity.ok(categoriaService.update(codigo, categoria));
//  }
//
//  @ApiOperation(value = "Exclui um único registro de categoria fornecido seu código")
//  @DeleteMapping("/{codigo}")
//  public ResponseEntity<Void> delete(@PathVariable Long codigo) {
//    categoriaService.delete(codigo);
//    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//  }

}
