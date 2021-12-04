package com.gvendas.gestaogvendas.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Endereco {
  @Column(name = "logradouro", length = 30)
  private String logradouro;

  @Column(name = "numero")
  private Integer numero;

  @Column(name = "complemento", length = 30)
  private String complemento;

  @Column(name = "bairro", length = 30)
  private String bairro;

  @Column(name = "cep", length = 30)
  private String cep;

  @Column(name = "cidade", length = 30)
  private String cidade;

  @Column(name = "estado", length = 30)
  private String estado;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Endereco endereco = (Endereco) o;
    return Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numero, endereco.numero) &&
        Objects.equals(complemento, endereco.complemento) && Objects.equals(bairro, endereco.bairro) &&
        Objects.equals(cep, endereco.cep) && Objects.equals(cidade, endereco.cidade) &&
        Objects.equals(estado, endereco.estado);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logradouro, numero, complemento, bairro, cep, cidade, estado);
  }
}