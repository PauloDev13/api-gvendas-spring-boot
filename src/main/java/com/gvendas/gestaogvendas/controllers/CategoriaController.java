package com.gvendas.gestaogvendas.controllers;

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

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @ApiOperation(value = "ListAll")
  @GetMapping
  public List<Categoria> listAll() {
    return categoriaService.listAll();
  }

  @ApiOperation(value = "FindById")
  @GetMapping("/{codigo}")
  public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long codigo) {
    Optional<Categoria> categoria = categoriaService.findById(codigo);
    return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
  }

  @ApiOperation(value = "Save")
  @PostMapping
  public ResponseEntity<Categoria> save(@Valid @RequestBody() Categoria categoria) {
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
  }

  @ApiOperation(value = "Update")
  @PutMapping("/{codigo}")
  public ResponseEntity<Categoria> update(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
    return ResponseEntity.ok(categoriaService.update(codigo, categoria));
  }

}
