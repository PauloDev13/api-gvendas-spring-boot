package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Categoria;
import com.gvendas.gestaogvendas.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
  private final CategoriaRepository categoriaRepository;

  public CategoriaService(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  public List<Categoria> listAll() {
    return categoriaRepository.findAll();
  }

  public Optional<Categoria> findById(Long id) {
    return categoriaRepository.findById(id);
  }
}
