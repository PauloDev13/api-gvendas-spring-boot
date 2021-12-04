package com.gvendas.gestaogvendas.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo", nullable = false)
  private Long id;

  @Column(name = "nome", nullable = false, length = 50)
  private String nome;

  @Column(name = "telefone", nullable = false, length = 20)
  private String telefone;

  @Column(name = "ativo", nullable = false)
  private Boolean ativo = false;

  @Embedded
  private Endereco endereco;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente) o;
    return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(telefone,
        cliente.telefone) && Objects.equals(ativo, cliente.ativo) && Objects.equals(endereco, cliente.endereco);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, telefone, ativo, endereco);
  }
}