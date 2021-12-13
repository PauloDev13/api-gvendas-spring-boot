package com.gvendas.gestaogvendas.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "cliente")
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo", nullable = false)
  private Long codigo;

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
    return Objects.equals(codigo, cliente.codigo) && Objects.equals(nome, cliente.nome) && Objects.equals(telefone,
        cliente.telefone) && Objects.equals(ativo, cliente.ativo) && Objects.equals(endereco, cliente.endereco);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo, nome, telefone, ativo, endereco);
  }
}