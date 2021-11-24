package com.gvendas.gestaogvendas.repositories;

import com.gvendas.gestaogvendas.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
  Categoria findByNome(String nome);
}
