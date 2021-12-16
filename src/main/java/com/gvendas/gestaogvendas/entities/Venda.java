package com.gvendas.gestaogvendas.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venda")
public class Venda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo", nullable = false)
  private Long codigo;

  @Column(name = "data", nullable = false)
  private LocalDate data;

  @ManyToOne(optional = false)
  @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
  private Cliente cliente;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Venda venda = (Venda) o;
    return Objects.equals(codigo, venda.codigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo);
  }
}