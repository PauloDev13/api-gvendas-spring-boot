package com.gvendas.gestaogvendas.repositories;

import com.gvendas.gestaogvendas.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
