package com.gvendas.gestaogvendas.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "item_venda")
public class ItemVenda {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo", nullable = false)
  private Long codigo;

  @Column(name = "quantidade", nullable = false)
  private Integer quantidade;

  @Column(name = "preco_vendido", nullable = false, precision = 10, scale = 2)
  private BigDecimal precoVendido;

  @ManyToOne(optional = false)
  @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
  private Produto produto;

  @ManyToOne(optional = false)
  @JoinColumn(name = "codigo_venda", referencedColumnName = "codigo")
  private Venda venda;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemVenda itemVenda = (ItemVenda) o;
    return codigo.equals(itemVenda.codigo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo);
  }
}