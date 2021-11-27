package com.gvendas.gestaogvendas.controllers;

import com.gvendas.gestaogvendas.dto.ProdutoRequestDTO;
import com.gvendas.gestaogvendas.dto.ProdutoResponseDTO;
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
import java.util.stream.Collectors;

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
  public List<ProdutoResponseDTO> listProductsByCategory(@PathVariable Long codigoCategoria) {
    return produtoService.listProductsByCategory(codigoCategoria)
        .stream()
        .map(ProdutoResponseDTO::ProductToDTO)
        .collect(Collectors.toList());
  }

  @ApiOperation(
      value = "Busca um único registro de Produto pelo seu código e o código da sua Categoria",
      nickname = "produtoPorCodigo"
  )
  @GetMapping("/{codigoProduto}")
  public ResponseEntity<ProdutoResponseDTO> findById(
      @PathVariable Long codigoProduto, @PathVariable Long codigoCategoria
  ) {
    Optional<Produto> produto = produtoService.findProductById(codigoProduto, codigoCategoria);
    return produto.map(value -> ResponseEntity.ok(
        ProdutoResponseDTO.ProductToDTO(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @ApiOperation(value = "Insere um registro de Produto", nickname="salvaProduto")
  @PostMapping
  public ResponseEntity<ProdutoResponseDTO> save(
      @PathVariable Long codigoCategoria, @Valid @RequestBody() ProdutoRequestDTO produtoDto
      ) {
    Produto produtoSaved = produtoService.save(codigoCategoria, produtoDto.DtoToProduct(codigoCategoria));
    return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.ProductToDTO(produtoSaved));
  }

  @ApiOperation(
      value = "Atualiza um único registro de Produto fornecido seu código e o código da sua Categoria",
      nickname = "atualizarCategoria"
  )
  @PutMapping("/{codigoProduto}")
  public ResponseEntity<ProdutoResponseDTO> update(

      @PathVariable Long codigoCategoria,
      @PathVariable Long codigoProduto,
      @Valid @RequestBody ProdutoRequestDTO produtoDto
  ) {
    Produto productUpdated = produtoService.update(
        codigoCategoria, codigoProduto, produtoDto.DtoToProduct(codigoProduto, codigoCategoria));
    return ResponseEntity.ok(ProdutoResponseDTO.ProductToDTO(productUpdated));
  }

  @ApiOperation(value = "Exclui um único registro de Produto fornecido seu código", nickname = "excluiProduto")
  @DeleteMapping("/{codigoProduto}")
  public ResponseEntity<Void> delete(@PathVariable Long codigoProduto, @PathVariable Long codigoCategoria) {
    produtoService.delete(codigoProduto, codigoCategoria);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
