package com.gvendas.gestaogvendas.controllers;

import com.gvendas.gestaogvendas.dtos.categoria.CategoriaRequestDTO;
import com.gvendas.gestaogvendas.dtos.categoria.CategoriaResponseDTO;
import com.gvendas.gestaogvendas.entities.Categoria;
import com.gvendas.gestaogvendas.services.CategoriaService;
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
@RequestMapping("/categorias")
@Api(tags = "Categoria")
public class CategoriaController {
  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @ApiOperation(value = "Lista todos os registros de categorias", nickname = "todasCategorias")
  @GetMapping
  public List<CategoriaResponseDTO> listAll() {
    return categoriaService.listAll().stream()
        .map(CategoriaResponseDTO::categoryToDTO).collect(Collectors.toList());
  }

  @ApiOperation(value = "Busca um único registro de categoria fornecido seu código", nickname = "categoriaPorCodigo")
  @GetMapping("/{codigo}")
  public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long codigo) {
    Optional<Categoria> categoria = categoriaService.findById(codigo);
    return categoria.map(value -> ResponseEntity.ok(
        CategoriaResponseDTO.categoryToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @ApiOperation(value = "Insere um registro de categoria", nickname = "salvaCategoria")
  @PostMapping
  public ResponseEntity<CategoriaResponseDTO> save(@Valid @RequestBody() CategoriaRequestDTO categoriaDto) {
    Categoria categoriaSaved = categoriaService.save(categoriaDto.DtoToCategory());
    return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.categoryToDTO(categoriaSaved));
  }

  @ApiOperation(
      value = "Atualiza um único registro de Categoria fornecido seu código",
      nickname = "atualizaCategoria"
  )
  @PutMapping("/{codigo}")
  public ResponseEntity<CategoriaResponseDTO> update(
      @PathVariable Long codigo,
      @Valid @RequestBody CategoriaRequestDTO categoriaDto
  ) {
    Categoria categoriaUpdated = categoriaService.update(codigo, categoriaDto.DtoToCategory());
    return ResponseEntity.ok(CategoriaResponseDTO.categoryToDTO(categoriaUpdated));
  }

  @ApiOperation(value = "Exclui um único registro de categoria fornecido seu código", nickname = "excluiCategoria")
  @DeleteMapping("/{codigo}")
  public ResponseEntity<Void> delete(@PathVariable Long codigo) {
    categoriaService.delete(codigo);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
