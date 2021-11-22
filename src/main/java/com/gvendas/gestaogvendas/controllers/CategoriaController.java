package com.gvendas.gestaogvendas.controllers;

import com.gvendas.gestaogvendas.entities.Categoria;
import com.gvendas.gestaogvendas.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
  private final CategoriaService categoriaService;

  public CategoriaController(CategoriaService categoriaService) {
    this.categoriaService = categoriaService;
  }

  @GetMapping
  public List<Categoria> listAll() {
    return categoriaService.listAll();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Optional<Categoria>> findById(@PathVariable Long codigo) {
    Optional<Categoria> categoria = categoriaService.findById(codigo);
    return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Categoria> save(@RequestBody() Categoria categoria) {
    return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
  }
}
