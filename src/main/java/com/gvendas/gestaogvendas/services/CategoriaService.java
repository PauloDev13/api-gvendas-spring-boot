package com.gvendas.gestaogvendas.services;

import com.gvendas.gestaogvendas.entities.Categoria;
import com.gvendas.gestaogvendas.exceptions.BusinessRulesException;
import com.gvendas.gestaogvendas.repositories.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    ValidateDuplicateCategory(categoria);
    return categoriaRepository.save(categoria);
  }

  public Categoria update(Long codigo, Categoria categoria) {
    Categoria categoriaSaved = validateCategory(codigo);
    ValidateDuplicateCategory(categoria);
    BeanUtils.copyProperties(categoria, categoriaSaved, "codigo");
    return categoriaRepository.save(categoriaSaved);
  }

  public void delete(Long codigo) {
    validateCategory(codigo);
    categoriaRepository.deleteById(codigo);
  }

  // Métodos auxiliares
  private Categoria validateCategory(Long id) {
    Optional<Categoria> categoria = findById(id);

    if (categoria.isEmpty()) {
      throw new EmptyResultDataAccessException(1);
    }
    return categoria.get();
  }

  private void ValidateDuplicateCategory(Categoria categoria) {
    Categoria categoriaFind = categoriaRepository.findByNome(categoria.getNome());

    if(categoriaFind != null && !Objects.equals(categoriaFind.getCodigo(), categoria.getCodigo())) {
      throw new BusinessRulesException(String.format("A categoria %s já existe", categoria.getNome().toUpperCase()));
    }
  }
}
