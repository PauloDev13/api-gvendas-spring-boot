package com.gvendas.gestaogvendas.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class Categoria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codigo;

  @Column(name = "nome")
  private String nome;

  // Constructors
  public Categoria() {}

  public Categoria(Long codigo) {
    this.codigo = codigo;
  }

  public Categoria(String nome) {
    this.nome = nome;
  }

  // Getters and Setters
  public Long getCodigo() {
    return codigo;
  }

  public void setCodigo(Long codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Categoria categoria = (Categoria) o;
    return Objects.equals(codigo, categoria.codigo) && Objects.equals(nome, categoria.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nome);
  }
}
