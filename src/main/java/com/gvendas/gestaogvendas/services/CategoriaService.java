package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Categoria;
import com.gvendas.gestaogvendas.repositories.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
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

  public Categoria save(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  public Categoria update(Long codigo, Categoria categoria) {
    Categoria categoriaSaved = validCategoria(codigo);
    BeanUtils.copyProperties(categoria, categoriaSaved, "codigo");
    return categoriaRepository.save(categoriaSaved);
  }

  private Categoria validCategoria(Long id) {
    Optional<Categoria> categoria = findById(id);

    if (categoria.isEmpty()) {
      throw new EmptyResultDataAccessException(1);
    }
    return categoria.get();
  }
}
